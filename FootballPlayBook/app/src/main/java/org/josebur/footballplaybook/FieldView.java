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


public class FieldView extends View {

    private Context _context;

    private Field _field;
    private FieldTransform _transform;
    private GestureDetector _gestureDetector;
    private FieldViewListener _listener;

    public interface FieldViewListener {
        void onPlayerLongPressed(Player p);
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        _context = context;

        _gestureDetector = new GestureDetector(context, new PlayerLongPressGesture());
    }

    public void setField(Field field) {
        _field = field;
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
    protected void onDraw(Canvas canvas) {
        doDraw(canvas);
    }

    private void doDraw(Canvas c) {
        if (_field != null) {
            _field.draw(c, _transform);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (_field != null) {
            _transform = new FieldTransform(w, h, _field.getFieldWidth(), _field.getFieldLength());
        }
    }

    private class PlayerLongPressGesture extends GestureDetector.SimpleOnGestureListener {

        public PlayerLongPressGesture() {
            setLongClickable(true);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Player p = _field.hitTest(e.getX(), e.getY(), _transform);
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
