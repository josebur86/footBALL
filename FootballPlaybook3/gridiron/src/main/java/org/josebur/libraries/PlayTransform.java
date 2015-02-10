package org.josebur.libraries;

import org.josebur.libraries.math.Matrix2D;

public class PlayTransform {

    private PlayFieldProperties _play;
    private ViewPort _view;

    private Matrix2D _pixelToFeet;
    private Matrix2D _feetToPixel;

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort) {
        _play = playFieldProperties;
        _view = viewPort;

        float feetToPixelScale = _view.width() / FieldMeasurements.FullFieldWidth();

        // X Offset
        float fieldWidthInPixels = FieldMeasurements.FullFieldWidth() * feetToPixelScale;
        float offsetX = -(fieldWidthInPixels - _view.width()) * 0.5f;

        // Y Offset
        float fieldLengthInPixels = FieldMeasurements.FullFieldLength() * feetToPixelScale;
        float offsetY = -(fieldLengthInPixels - _view.height()) * 0.5f;

        _feetToPixel = new Matrix2D()
                .scaleX(feetToPixelScale)
                .scaleY(feetToPixelScale)
                .translateX(offsetX)
                .translateY(offsetY);

        _pixelToFeet = _feetToPixel.invert();
    }

    // Spike ----------- //
    public boolean zoom(float zoomFactor) {
        float feetToPixelScale = _feetToPixel.scaleX() * zoomFactor;

        // X Offset
        float fieldWidthInPixels = FieldMeasurements.FullFieldWidth() * feetToPixelScale;
        float offsetX = -(fieldWidthInPixels - _view.width()) * 0.5f;

        // Y Offset
        float fieldLengthInPixels = FieldMeasurements.FullFieldLength() * feetToPixelScale;
        float offsetY = -(fieldLengthInPixels - _view.height()) * 0.5f;

        _feetToPixel = new Matrix2D()
                .scaleX(feetToPixelScale)
                .scaleY(feetToPixelScale)
                .translateX(offsetX)
                .translateY(offsetY);

        _pixelToFeet = _feetToPixel.invert();

        return true;
    }
    // ----------------- //

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
