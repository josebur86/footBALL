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

        float scale = _play.width() / _view.width();
        float viewHeightInFeet = _view.height() * scale;
        float viewTopInFeet = _play.ballSpotFeetY() - viewHeightInFeet * 0.5f;

        _pixelToFeet = new Matrix2D()
            .scaleX(scale)
            .scaleY(scale)
            .translateY(viewTopInFeet);

        _feetToPixel = _pixelToFeet.invert(); // FIXME: this could return null.
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
}
