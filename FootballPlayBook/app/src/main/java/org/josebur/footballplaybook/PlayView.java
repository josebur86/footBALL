package org.josebur.footballplaybook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
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

        private final int _fieldLengthFeet = 60;
        private final int _fieldWidthFeet = 160;

        private FieldTransform _fieldTransform;

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
            _fieldTransform = new FieldTransform(_canvasWidth, _canvasHeight, _fieldWidthFeet, _fieldLengthFeet);
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

            for (int yardLine = 1; yardLine < 20; yardLine++) {
                if (yardLine % 5 == 0) {
                    drawFiveYardIncrementLine(c, yardLine);
                    drawVerticalHashMark(c, yardLine);
                }
                else {
                    drawHashMark(c, yardLine);
                }
            }
        }

        private void drawFiveYardIncrementLine(Canvas c, int yardLine) {
            float yardPoint = _fieldTransform.getYardPoint(yardLine);
            c.drawLine(0, yardPoint, _canvasWidth, yardPoint, _yardLinePaint);
        }

        private void drawVerticalHashMark(Canvas c, int yardLine) {
            float hashLengthY = _fieldTransform.getPointFromFeet(0.0f, 1.0f).y;
            PointF leftHash = _fieldTransform.getPointFromFeet(Field.getLeftVerticalHashPosition(), yardLine * 3.0f);
            PointF rightHash = _fieldTransform.getPointFromFeet(Field.getRightVerticalHashPosition(), yardLine * 3.0f);

            c.drawLine(leftHash.x, leftHash.y - hashLengthY * 0.5f, leftHash.x, leftHash.y + hashLengthY * 0.5f, _yardLinePaint);
            c.drawLine(rightHash.x, rightHash.y - hashLengthY * 0.5f, rightHash.x, rightHash.y + hashLengthY * 0.5f, _yardLinePaint);
        }

        private void drawHashMark(Canvas c, int yardLine) {

            float hashLengthX = _fieldTransform.getPointFromFeet(2.0f, 0.0f).x;

            float leftSideline = _fieldTransform.getPointFromFeet(Field.getLeftSideLineHashPosition(), 0.0f).x;
            float rightSideline = _fieldTransform.getPointFromFeet(Field.getRightSideLineHashPosition(), 0.0f).x;
            float yardPoint = _fieldTransform.getYardPoint(yardLine);

            c.drawLine(leftSideline, yardPoint, leftSideline + hashLengthX, yardPoint, _yardLinePaint);
            c.drawLine(rightSideline - hashLengthX, yardPoint, rightSideline, yardPoint, _yardLinePaint);

            float leftHash = _fieldTransform.getPointFromFeet(Field.getLeftHashPosition(), 0.0f).x;
            float rightHash = _fieldTransform.getPointFromFeet(Field.getRightHashPosition(), 0.0f).x;
            c.drawLine(leftHash, yardPoint, leftHash + hashLengthX, yardPoint, _yardLinePaint);
            c.drawLine(rightHash - hashLengthX, yardPoint, rightHash, yardPoint, _yardLinePaint);
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
