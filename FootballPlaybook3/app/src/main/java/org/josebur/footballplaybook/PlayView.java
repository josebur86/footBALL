package org.josebur.footballplaybook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import org.josebur.libraries.Field;
import org.josebur.libraries.FieldPainter;
import org.josebur.libraries.PlayFieldProperties;
import org.josebur.libraries.PlayTransform;
import org.josebur.libraries.ViewPort;

public class PlayView extends View implements ViewPort {

    Field _field;
    PlayFieldProperties _playFieldProperties;
    PlayTransform _playTransform;
    CanvasFieldPainter _painter;

    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _painter = new CanvasFieldPainter(null);
        _field = null;
        _playTransform = null;
    }

    public void setField(Field field) {
        _field = field;
    }

    public void setPlayFieldProperties(PlayFieldProperties playFieldProperties) {
        _playFieldProperties = playFieldProperties;
    }

    /**
     * Returns the width of the view.
     * @return width of the view in pixels.
     */
    @Override
    public int width() {
        return getWidth();
    }

    /**
     * Return the height of the view.
     * @return height of the view in pixels.
     */
    @Override
    public int height() {
        return getHeight();
    }

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w    Current width of this view.
     * @param h    Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (_playFieldProperties == null) return;

        _playTransform = new PlayTransform(_playFieldProperties, this);
    }

    /**
     * Draws the field to the canvas.
     *
     * @param canvas the canvas on which the field will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (_field == null || _playTransform == null) return;

        _painter.setCanvas(canvas);
        _painter.drawGrass(); // FIXME: this should be moved to the field draw method.
        _field.draw(_painter, _playTransform);
    }

    private class CanvasFieldPainter implements FieldPainter {

        Canvas _canvas;
        Paint _linePaint;

        public CanvasFieldPainter(Canvas canvas)
        {
            _canvas = canvas;
            _linePaint = new Paint();
            _linePaint.setAlpha(190);
            _linePaint.setColor(Color.WHITE);
            _linePaint.setStrokeWidth(5);
        }

        public void setCanvas(Canvas canvas) {
            _canvas = canvas;
        }

        public void drawGrass() {
            _canvas.drawRGB(0, 100, 0);
        }

        @Override
        public void drawSideline(float left, float top, float right, float bottom) {
            _canvas.drawRect(left, top, right, bottom, _linePaint);
        }

        @Override
        public void drawEndline(float left, float top, float right, float bottom) {
            _canvas.drawRect(left, top, right, bottom, _linePaint);
        }
    }
}
