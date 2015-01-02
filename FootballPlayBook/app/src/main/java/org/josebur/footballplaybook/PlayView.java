package org.josebur.footballplaybook;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class PlayView extends SurfaceView implements SurfaceHolder.Callback {

    public class PlayThread extends Thread {

        private Context _context;
        private SurfaceHolder _surfaceHolder;

        private final Object _runLock = new Object();
        private boolean _running = false;

        private Field _field;

        public PlayThread(SurfaceHolder surfaceHolder, Context context) {
            _surfaceHolder = surfaceHolder;
            _context = context;

            _field = new Field();
        }

        public void setFormation(Formation formation) { _field.setFormation(formation); }

        public void setSurfaceSize(int width, int height) {
            _field.setCanvasSize(width, height);
        }

        public void setRunning(boolean running) {
            synchronized (_runLock) {
                _running = running;
            }
        }

        public Player hitTest(float x, float y) {
            return _field.hitTest(x, y);
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
            _field.draw(c);
        }
    }

    private PlayThread _thread = null;
    private GestureDetector _gestureDetector;

    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        _thread = new PlayThread(surfaceHolder, context);

        _gestureDetector = new GestureDetector(context, new PlayerLongPressGesture());
    }

    public void setFormation(Formation formation) {
        _thread.setFormation(formation);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return _gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
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

    private class PlayerLongPressGesture extends GestureDetector.SimpleOnGestureListener {

        public PlayerLongPressGesture() {
            setLongClickable(true);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Player p = _thread.hitTest(e.getX(), e.getY());
            if (p != null) {
                Log.d("onLongPress", p.label());

                PlayerDragShadowBuilder shadowBuilder = new PlayerDragShadowBuilder();
                ClipData dragData = ClipData.newPlainText("Player", p.label());

                startDrag(dragData, shadowBuilder, null, 0);
            }
            else {
                Log.d("onLongPress", "No Player");
            }
        }
    }

    private class PlayerDragShadowBuilder extends DragShadowBuilder {
        private ShapeDrawable _shadow;

        PlayerDragShadowBuilder(/* Player p*/) {
            super(null);

            Shape s = new OvalShape();
            s.resize(240.f, 240.f); // TODO: these are magic numbers ATM.
            _shadow = new ShapeDrawable(s);
            _shadow.getPaint().setColor(Color.RED);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {

            int width = 240;
            int height = 240;
            shadowSize.set(width, height); // TODO: these are the same magic numbers as in the ctor.
            shadowTouchPoint.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            _shadow.draw(canvas);
        }
    }
}
