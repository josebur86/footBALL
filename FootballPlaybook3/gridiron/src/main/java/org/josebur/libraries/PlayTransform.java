package org.josebur.libraries;

import org.josebur.libraries.math.Matrix2D;

public class PlayTransform {

    private PlayFieldProperties _play;
    private ViewPort _view;
    private FieldMeasurements _measurements;

    private Matrix2D _pixelToFeet;
    private Matrix2D _feetToPixel;

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort) {
        _play = playFieldProperties;
        _view = viewPort;
        _measurements = new NflFieldMeasurements();

        float feetToPixelScale = _view.width() / _measurements.FullFieldWidth2();

        // X Offset
        float fieldWidthInPixels = _measurements.FullFieldWidth2() * feetToPixelScale;
        float offsetX = -(fieldWidthInPixels - _view.width()) * 0.5f;

        // Y Offset
        float fieldLengthInPixels = _measurements.FullFieldLength2() * feetToPixelScale;
        float offsetY = -(fieldLengthInPixels - _view.height()) * 0.5f;

        _feetToPixel = new Matrix2D()
                .scaleX(feetToPixelScale)
                .scaleY(feetToPixelScale)
                .translateX(offsetX)
                .translateY(offsetY);

        _pixelToFeet = _feetToPixel.invert();
    }

    public boolean zoom(float zoomFactor) {
        float feetToPixelScale = _feetToPixel.scaleX() * zoomFactor;
        float pixelToFeetScale = 1.f / feetToPixelScale;

        float xOffset = -((viewCenterInFeetX() - (_view.width() * pixelToFeetScale * 0.5f)) * feetToPixelScale);

        // Y Offset
        float fieldLengthInPixels = _measurements.FullFieldLength2() * feetToPixelScale;
        float offsetY = -(fieldLengthInPixels - _view.height()) * 0.5f;

        _feetToPixel = new Matrix2D()
                .scaleX(feetToPixelScale)
                .scaleY(feetToPixelScale)
                .translateX(xOffset)
                .translateY(offsetY);

        _pixelToFeet.invert();

//        float feetToPixelScale = _feetToPixel.scaleX() * zoomFactor;
//
//        // X Offset
//        float fieldWidthInPixels = FieldMeasurements.FullFieldWidth() * feetToPixelScale;
//        float offsetX = -(fieldWidthInPixels - _view.width()) * 0.5f;
//
//        // Y Offset
//        float fieldLengthInPixels = FieldMeasurements.FullFieldLength() * feetToPixelScale;
//        float offsetY = -(fieldLengthInPixels - _view.height()) * 0.5f;
//
//        _feetToPixel = new Matrix2D()
//                .scaleX(feetToPixelScale)
//                .scaleY(feetToPixelScale)
//                .translateX(offsetX)
//                .translateY(offsetY);
//
//        _pixelToFeet = _feetToPixel.invert();

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
