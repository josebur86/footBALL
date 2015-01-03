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


public class FieldView extends SurfaceView implements SurfaceHolder.Callback {

    private Field _field;
    private FieldTransform _transform;
    private FieldDrawThread _thread = null;
    private GestureDetector _gestureDetector;
    private FieldViewListener _listener;

    public interface FieldViewListener {
        void onPlayerLongPressed(Player p);
        void onDrag(float deltaX, float deltaY);
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        _thread = new FieldDrawThread(surfaceHolder, context);
        _gestureDetector = new GestureDetector(context, new PlayerLongPressGesture());
    }

    public void setField(Field field) {
        _field = field;

        _thread.setRunning(false);
        _thread.setField(field);
        _thread.setRunning(true);
    }

    public FieldTransform fieldTransform() {
        return _transform;
    }

    public void setFieldViewListener(FieldViewListener listener) {
        _listener = listener;
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
        if (_field != null) {
            _transform = new FieldTransform(width, height, _field.getFieldWidth(), _field.getFieldLength());
            _thread.setFieldTransform(_transform);
        }
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
                Log.e("FieldView.killThread", "Interrupted Exception thrown.");
            }
        }
    }

    public class FieldDrawThread extends Thread {

        private Context _context;
        private final SurfaceHolder _surfaceHolder;

        private final Object _runLock = new Object();
        private boolean _running = false;

        private Field _field = null;
        private FieldTransform _transform;

        public FieldDrawThread(SurfaceHolder surfaceHolder, Context context) {
            _surfaceHolder = surfaceHolder;
            _context = context;
        }

        public void setField(Field field) {
            _field = field;
        }

        public void setFieldTransform(FieldTransform transform) {
            _transform = transform;
        }

        public void setRunning(boolean running) {
            synchronized (_runLock) {
                _running = running;
            }
        }

        public Player hitTest(float x, float y) {
            if (_field != null) {
                return _field.hitTest(x, y, _transform);
            }

            return null;
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
            if (_field != null) {
                _field.draw(c, _transform);
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
                if (_listener != null)
                {
                    _listener.onPlayerLongPressed(p);
                }
            }
            else {
                Log.d("onLongPress", "No Player");
            }
        }
    }

    public static class PlayerDragShadowBuilder extends DragShadowBuilder {
        private static ShapeDrawable _shadow;

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
