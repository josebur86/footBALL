package org.josebur.libraries.helpers;

import org.josebur.libraries.FieldMeasurements;
import org.josebur.libraries.PlayFieldProperties;

public class FakePlayFieldProperties implements PlayFieldProperties {

    private float _width;
    private float _height;
    private float _ballSpotX;
    private float _ballSpotY;

    public FakePlayFieldProperties(float width, float height) {
        _width = width;
        _height = height;
        _ballSpotX = (FieldMeasurements.Width + FieldMeasurements.BorderSize * 2) / 2.f;
        _ballSpotY = (FieldMeasurements.Length +
                      FieldMeasurements.EndZoneLength * 2 +
                      FieldMeasurements.BorderSize * 2) / 2.f;
    }

    @Override
    public float width() {
        return _width;
    }

    @Override
    public float length() {
        return _height;
    }

    @Override
    public float ballSpotFeetX() {
        return _ballSpotX;
    }

    @Override
    public float ballSpotFeetY() {
        return _ballSpotY;
    }
}
