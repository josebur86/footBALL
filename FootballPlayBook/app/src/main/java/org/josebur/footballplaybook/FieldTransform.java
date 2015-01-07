package org.josebur.footballplaybook;

import android.graphics.Matrix;
import android.graphics.PointF;

public class FieldTransform {

    private Matrix _feetToPointTransform;
    private Matrix _pointToFeetTransform;

    public static int kFeetPerYard = 3;

    public FieldTransform(int width, int height, int fieldWidthFeet, int fieldLengthFeet ) {
        _feetToPointTransform = new Matrix();
        _feetToPointTransform.setScale(width / (float)fieldWidthFeet,
                                       height / (float)fieldLengthFeet);

        _pointToFeetTransform = new Matrix();
        _feetToPointTransform.invert(_pointToFeetTransform);
    }

    public PointF getPointFromFeet(PointF pointFeet)
    {
        return getPointFromFeet(pointFeet.x, pointFeet.y);
    }

    public PointF getPointFromFeet(float x, float y) {
        float[] pts = { x, y };
        _feetToPointTransform.mapPoints(pts);

        return new PointF(pts[0], pts[1]);
    }

    public PointF getFeetFromPoint(PointF pointPixel) {
        return getFeetFromPoint(pointPixel.x, pointPixel.y);
    }

    public PointF getFeetFromPoint(float x, float y) {
        float[] pts = { x, y };
        _pointToFeetTransform.mapPoints(pts);

        return new PointF(pts[0], pts[1]);
    }

    public float getYardPoint(int yardLine) {

        float[] pts = { 0.0f, yardLine * kFeetPerYard };
        _feetToPointTransform.mapPoints(pts);
        return pts[1];
    }

    public float getVerticalYardLength() {
        return getYardPoint(1);
    }

    public float xFeetLengthFromPoint(float xPoint)
    {
        return getFeetFromPoint(xPoint, 0).x;
    }
}
