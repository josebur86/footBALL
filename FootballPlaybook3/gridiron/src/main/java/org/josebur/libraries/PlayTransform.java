package org.josebur.libraries;

import org.josebur.libraries.math.Matrix2D;
import org.josebur.libraries.math.Point;

public class PlayTransform {

    private PlayFieldProperties _play;
    private ViewPort _view;
    private FieldMeasurements _measurements;

    private Matrix2D _pixelToFeet;
    private Matrix2D _feetToPixel;

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort, FieldMeasurements measurements) {
        _play = playFieldProperties;
        _view = viewPort;
        _measurements = measurements;

        float feetToPixelScale = _view.width() / _measurements.FullFieldWidth();
        float pixelToFeetScale = 1.f / feetToPixelScale;
        Position viewCenter = new Position(_measurements.FullFieldWidth() * 0.5f,
                                           _measurements.FullFieldLength() * 0.5f);
        calculateMatrices(feetToPixelScale, pixelToFeetScale, viewCenter);
    }

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort) {
        this(playFieldProperties, viewPort, new NflFieldMeasurements());
    }

    public boolean zoom(float zoomFactor) {
        float feetToPixelScale = _feetToPixel.scaleX() * zoomFactor;
        float pixelToFeetScale = 1.f / feetToPixelScale;
        return calculateMatrices(feetToPixelScale, pixelToFeetScale, viewCenter());
    }

    private boolean calculateMatrices(float feetToPixelScale, float pixelToFeetScale,
                                      Position viewCenter) {
        float xOffset = -((viewCenter.feetX() - (_view.width() * pixelToFeetScale * 0.5f)) * feetToPixelScale);
        float yOffset = -((viewCenter.feetY() - (_view.height() * pixelToFeetScale * 0.5f)) * feetToPixelScale);

        _feetToPixel = new Matrix2D()
                .scaleX(feetToPixelScale)
                .scaleY(feetToPixelScale)
                .translateX(xOffset)
                .translateY(yOffset);

        _pixelToFeet = _feetToPixel.invert();

        return true;
    }

    public boolean pan(float xOffset, float yOffset) {
        _feetToPixel = _feetToPixel
                .translateX(-xOffset)
                .translateY(-yOffset);

        _pixelToFeet = _feetToPixel.invert();
        return true;
    }

    public float pixelToFeetX(float pixel) {
        return _pixelToFeet.multiplyPoint(pixel, 0).x();
    }

    public float pixelToFeetY(float pixel) {
        return _pixelToFeet.multiplyPoint(0, pixel).y();
    }

    public float feetToPixelX(float x) {
        return _feetToPixel.multiplyPoint(x, 0).x();
    }

    public float feetToPixelY(float y) {
        return _feetToPixel.multiplyPoint(0, y).y();
    }

    public float widthFromFeet(float width) {
        return width * _feetToPixel.scaleX();
    }

    public float lengthFromFeet(float length) {

        return length * _feetToPixel.scaleY();
    }

    public Position viewCenter() {
        Point center = _pixelToFeet.multiplyPoint(_view.width() * 0.5f,
                                                  _view.height() * 0.5f);
        return new Position(center.x(), center.y());
    }

}
