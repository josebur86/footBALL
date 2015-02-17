package org.josebur.libraries.helpers;

import org.josebur.libraries.FieldMeasurements;
import org.josebur.libraries.NflFieldMeasurements;
import org.josebur.libraries.PlayFieldProperties;
import org.josebur.libraries.Position;

public class FakePlayFieldProperties implements PlayFieldProperties {

    private float _width;
    private float _height;
    private float _ballSpotX;
    private float _ballSpotY;

    public FakePlayFieldProperties(float width, float height) {
        FieldMeasurements measurements = new NflFieldMeasurements();
        _width = width;
        _height = height;
        _ballSpotX = (measurements.Width() + measurements.BorderSize() * 2) / 2.f;
        _ballSpotY = (measurements.Length() +
                      measurements.EndZoneLength() * 2 +
                      measurements.BorderSize() * 2) / 2.f;
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
    public Position ballSpot() {
        return new Position(_ballSpotX, _ballSpotY);
    }
}
