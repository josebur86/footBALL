package org.josebur.footballplaybook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.*;
import android.os.Process;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PlayView extends SurfaceView implements SurfaceHolder.Callback {

    public class PlayThread extends Thread {

        private Context _context;
        private SurfaceHolder _surfaceHolder;

        private final Object _runLock = new Object();
        private boolean _running = false;

        public PlayThread(SurfaceHolder surfaceHolder, Context context) {
            _surfaceHolder = surfaceHolder;
            _context = context;
        }

        public void setRunning(boolean running) {
            synchronized (_runLock) {
                _running = running;
            }
        }

        @Override
        public void run() {
            boolean once = true;
            while (_running) {

                if (once) {
                    long id  = Thread.currentThread().getId();
                    Log.d("run()", "Thread ID: " + Long.toString(id));
                    once = false;
                }

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
