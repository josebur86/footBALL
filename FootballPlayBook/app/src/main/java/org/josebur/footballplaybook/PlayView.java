package org.josebur.footballplaybook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PlayView extends SurfaceView implements SurfaceHolder.Callback {

    public class PlayThread extends Thread {

        private Context _context;
        private SurfaceHolder _surfaceHolder;

        private final Object _runLock = new Object();
        private boolean _running = false;

        private Paint _yardLinePaint = null;

        private int _canvasWidth = 0;
        private int _canvasHeight = 0;

        public PlayThread(SurfaceHolder surfaceHolder, Context context) {
            _surfaceHolder = surfaceHolder;
            _context = context;

            _yardLinePaint = new Paint();
            _yardLinePaint.setColor(Color.WHITE);
            _yardLinePaint.setStrokeWidth(5);
        }

        public void setSurfaceSize(int width, int height) {
            _canvasWidth = width;
            _canvasHeight = height;
        }

        public void setRunning(boolean running) {
            synchronized (_runLock) {
                _running = running;
            }
        }

        @Override
        public void run() {
            while (_running) {

                Canvas c = null;
                try {
                    c = _surfaceHolder.lockCanvas();

                    synchronized (_surfaceHolder) {
                        synchronized (_runLock) {
                            if (_running) doDraw(c);
                        }
                    }
                } finally {
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        private void doDraw(Canvas c) {
            c.drawRGB(0, 100, 0);

            float oneYard = _canvasHeight / 20.0f;
            for (int i = 5; i < 20; i += 5) {
                drawFiveYardIncrementLine(c, oneYard * i);
            }

            float oneFoot = _canvasWidth / 160.0f;
            float oneInch = oneFoot / 12.0f;

            for (int i = 1; i < 20; i++) {
                if (i % 5 == 0) {
                    float leftHash = oneFoot * 70.0f + oneInch * 9.0f;
                    float rightHash = _canvasWidth - (oneFoot * 70.0f + oneInch * 9.0f);
                    float hashLength = oneYard * (1.0f/3.0f);
                    drawVerticalHashMark(c, oneYard, leftHash + hashLength, rightHash - hashLength, oneYard * i);
                }
                else {
                    drawHashMark(c, oneFoot, oneInch, oneYard, oneYard * i);
                }
            }
        }

        private void drawFiveYardIncrementLine(Canvas c, float y) {
            c.drawLine(0, y, _canvasWidth, y, _yardLinePaint);
        }

        private void drawVerticalHashMark(Canvas c, float oneYard, float leftHash, float rightHash, float y) {
            float hashLength = oneYard * (1.0f/3.0f);

            c.drawLine(leftHash, y - hashLength * 0.5f, leftHash, y + hashLength * 0.5f, _yardLinePaint);
            c.drawLine(rightHash, y -  hashLength * 0.5f, rightHash, y + hashLength * 0.5f, _yardLinePaint);
        }

        private void drawHashMark(Canvas c, float oneFoot, float oneInch, float oneYard, float y) {

            float hashLength = oneYard * (1.0f/3.0f);

            float leftSideline = 6.0f * oneInch;
            float rightSideline = _canvasWidth - 6.0f * oneInch;
            c.drawLine(leftSideline, y, leftSideline + hashLength, y, _yardLinePaint);
            c.drawLine(rightSideline - hashLength, y, rightSideline, y, _yardLinePaint);

            float leftHash = oneFoot * 70.0f + oneInch * 9.0f;
            float rightHash = _canvasWidth - (oneFoot * 70.0f + oneInch * 9.0f);
            c.drawLine(leftHash, y, leftHash + hashLength, y, _yardLinePaint);
            c.drawLine(rightHash - hashLength, y, rightHash, y, _yardLinePaint);
        }
    }

    private PlayThread _thread = null;



    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        _thread = new PlayThread(surfaceHolder, context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread.setRunning(true);
        _thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        _thread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
}
