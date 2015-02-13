package org.josebur.libraries;

import org.josebur.libraries.math.Matrix2D;

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

        float viewCenterInFeetX = _measurements.FullFieldWidth() * 0.5f;
        float viewCenterInFeetY = _measurements.FullFieldLength() * 0.5f;

        calculateMatrices(feetToPixelScale, pixelToFeetScale, viewCenterInFeetX, viewCenterInFeetY);
    }

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort) {
        this(playFieldProperties, viewPort, new NflFieldMeasurements());
    }

    public boolean zoom(float zoomFactor) {
        float feetToPixelScale = _feetToPixel.scaleX() * zoomFactor;
        float pixelToFeetScale = 1.f / feetToPixelScale;

        return calculateMatrices(feetToPixelScale, pixelToFeetScale, viewCenterInFeetX(), viewCenterInFeetY());
    }

    private boolean calculateMatrices(float feetToPixelScale, float pixelToFeetScale,
                                      float viewCenterInFeetX, float viewCenterInFeetY) {
        float xOffset = -((viewCenterInFeetX - (_view.width() * pixelToFeetScale * 0.5f)) * feetToPixelScale);
        float yOffset = -((viewCenterInFeetY - (_view.height() * pixelToFeetScale * 0.5f)) * feetToPixelScale);

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
        return _pixelToFeet.multiplyPointX(pixel);
    }

    public float pixelToFeetY(float pixel) {
        return _pixelToFeet.multiplyPointY(pixel);
    }

    public float feetToPixelX(float x) {
        return _feetToPixel.multiplyPointX(x);
    }

    public float feetToPixelY(float y) {
        return _feetToPixel.multiplyPointY(y);
    }

    public float widthFromFeet(float width) {
        return width * _feetToPixel.scaleX();
    }

    public float lengthFromFeet(float length) {

        return length * _feetToPixel.scaleY();
    }

    public float viewCenterInFeetX() {
        return _pixelToFeet.multiplyPointX(_view.width() * 0.5f);
    }

    public float viewCenterInFeetY() {
        return _pixelToFeet.multiplyPointY(_view.height() * 0.5f);
    }
}
