package org.josebur.footballplaybook;

import android.graphics.Matrix;
import android.graphics.PointF;

public class FieldTransform {

    private int _height;
    private int _width;

    private int _fieldLengthFeet;
    private int _fieldWidthFeet;

    private Matrix _feetToPointTransform;

    public FieldTransform(int width, int height, int fieldWidthFeet, int fieldLengthFeet ) {
        _height = height;
        _fieldLengthFeet = fieldLengthFeet;

        _width = width;
        _fieldWidthFeet = fieldWidthFeet;

        _feetToPointTransform = new Matrix();
        _feetToPointTransform.setScale(_width / (float)_fieldWidthFeet,
                                       _height / (float)_fieldLengthFeet);
    }

    public PointF getPointFromFeet(float x, float y) {
        float[] pts = { x, y };
        _feetToPointTransform.mapPoints(pts);

        return new PointF(pts[0], pts[1]);
    }

    public float getYardPoint(int yardLine) {

        float[] pts = { 0.0f, yardLine * 3.0f };
        _feetToPointTransform.mapPoints(pts);
        return pts[1];
    }

    public float getVerticalYardLength() {
        return getYardPoint(1);
    }
}
