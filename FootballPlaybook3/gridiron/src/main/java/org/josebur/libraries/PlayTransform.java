package org.josebur.libraries;

public class PlayTransform {

    private PlayFieldProperties _play;
    private ViewPort _view;

    public PlayTransform(PlayFieldProperties playFieldProperties, ViewPort viewPort) {
        _play = playFieldProperties;
        _view = viewPort;
    }

    public float pixelToFeetX(float pixel) {

        float scale = _play.width() / _view.width();

        return pixel * scale;
    }

    public float pixelToFeetY(float pixel) {

        float scale = _play.width() / _view.width();

        float viewHeightInFeet = _view.height() * scale;
        float viewTopInFeet = _play.ballSpotFeetY() - viewHeightInFeet * 0.5f;

        return pixel * scale + viewTopInFeet;
    }
}
