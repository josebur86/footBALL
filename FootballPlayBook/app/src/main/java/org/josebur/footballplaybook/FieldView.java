package org.josebur.footballplaybook;

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
import android.view.View;


public class FieldView extends View {

    private Context _context;

    private Field _field;
    private FieldTransform _transform;
    private GestureDetector _gestureDetector;
    private FieldViewListener _listener;

    public interface FieldViewListener {
        void onPlayerLongPressed(IPlayer p);
        void onPlayerTapped(IPlayer p);
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        _context = context;

        _gestureDetector = new GestureDetector(context, new PlayerLongPressGesture());

        // Setup a fake transform initially.
        _transform = new FieldTransform(1,1,1,1);
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
        public boolean onDown(MotionEvent e) {
            HitTestResult hitTestResult = _field.hitTest(e.getX(), e.getY(), _transform);
            if (hitTestResult != null &&
                    hitTestResult.hitTarget() == HitTarget.DragHandle &&
                    _listener != null)
            {
                _listener.onPlayerLongPressed(hitTestResult.player());
                return true;
            } else {
                Log.d("onLongPress", "No Player");
            }

            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            HitTestResult hitTestResult = _field.hitTest(e.getX(), e.getY(), _transform);
            if (hitTestResult != null &&
                    hitTestResult.hitTarget() == HitTarget.Player &&
                    _listener != null) {
               _listener.onPlayerTapped(hitTestResult.player());
                return true;
            } else {
                Log.d("onSingleTapConfirmed", "No Player");
            }

            return false;
        }
    }

    public static class PlayerDragShadowBuilder extends DragShadowBuilder {
        PlayerDragShadowBuilder(/* Player p*/) {
            super(null);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            shadowSize.set(0, 0);
            shadowTouchPoint.set(0, 0);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            // Don't draw any shadow.
        }
    }
}
