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
        float feetToPixelScale = _feetToPixel.scale() * zoomFactor;
        float pixelToFeetScale = 1.f / feetToPixelScale;
        return calculateMatrices(feetToPixelScale, pixelToFeetScale, viewCenter());
    }

    private boolean calculateMatrices(float feetToPixelScale, float pixelToFeetScale,
                                      Position viewCenter) {
        float xOffset = -((viewCenter.feetX() - (_view.width() * pixelToFeetScale * 0.5f)) * feetToPixelScale);
        float yOffset = -((viewCenter.feetY() - (_view.height() * pixelToFeetScale * 0.5f)) * feetToPixelScale);

        _feetToPixel = new Matrix2D()
                .scale(feetToPixelScale)
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

    public Position pixelToFeet(Pixel p) {
        Point position = _pixelToFeet.multiplyPoint(p.x(), p.y());
        return new Position(position.x(), position.y());
    }

    public Pixel feetToPixel(Position p) {
        Point pixel = _feetToPixel.multiplyPoint(p.feetX(), p.feetY());
        return new Pixel(pixel.x(), pixel.y());
    }

    public float widthFromFeet(float width) {
        return width * _feetToPixel.scale();
    }

    public float lengthFromFeet(float length) {

        return length * _feetToPixel.scale();
    }

    public Position viewCenter() {
        Point center = _pixelToFeet.multiplyPoint(_view.width() * 0.5f,
                                                  _view.height() * 0.5f);
        return new Position(center.x(), center.y());
    }

}
