package org.josebur.libraries;

import org.josebur.libraries.math.Matrix2D;

public class PlayTransform {

    private PlayFieldProperties _play;
    private ViewPort _view;

    private Matrix2D _matrix;

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort) {
        _play = playFieldProperties;
        _view = viewPort;

        float scale = _play.width() / _view.width();
        float viewHeightInFeet = _view.height() * scale;
        float viewTopInFeet = _play.ballSpotFeetY() - viewHeightInFeet * 0.5f;

        _matrix = new Matrix2D();
        _matrix.scaleX(scale);
        _matrix.scaleY(scale);
        _matrix.translateY(viewTopInFeet);
    }

    public float pixelToFeetX(float pixel) {
        return _matrix.multiplyPointX(pixel);
    }

    public float pixelToFeetY(float pixel) {
        return _matrix.multiplyPointY(pixel);
    }

    // TODO: feet to pixel
}
