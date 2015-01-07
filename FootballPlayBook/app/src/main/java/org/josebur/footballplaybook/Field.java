package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Field {

    private Formation _formation;
    private Paint _yardLinePaint;

    public Field() {
        _formation = new Formation();

        _yardLinePaint = new Paint();
        _yardLinePaint.setColor(Color.WHITE);
        _yardLinePaint.setAlpha(190);
        _yardLinePaint.setStrokeWidth(5);
    }

    public int getFieldWidth() {
        return 160;
    }

    public int getFieldLength() {
        return 60;
    }

    public void setFormation(Formation formation) {
        _formation = formation;
    }

    public Formation formation() {
        return _formation;
    }

    public void draw(Canvas c, FieldTransform transform) {
        c.drawRGB(0, 100, 0);

        int totalYards = getFieldLength() / FieldTransform.kFeetPerYard;
        for (int yardLine = 1; yardLine < totalYards; yardLine++) {
            if (yardLine % 5 == 0) {
                drawFiveYardIncrementLine(c, transform, yardLine);
                drawVerticalHashMark(c, transform, yardLine);
            }
            else {
                drawHashMark(c, transform, yardLine);
            }
        }

        _formation.draw(c, transform);
    }

    public HitTestResult hitTest(float pixelX, float pixelY, FieldTransform transform) {
        return  _formation.hitTest(pixelX, pixelY, transform);
    }

    private void drawFiveYardIncrementLine(Canvas c, FieldTransform transform, int yardLine) {
        float yardPoint = transform.getYardPoint(yardLine);
        float canvasWidth = transform.getPointFromFeet(getFieldWidth(), 0.0f).x;
        c.drawLine(0, yardPoint, canvasWidth, yardPoint, _yardLinePaint);
    }

    private void drawVerticalHashMark(Canvas c, FieldTransform transform, int yardLine) {
        float hashLengthY = transform.getPointFromFeet(0.0f, 1.0f).y;
        PointF leftHash = transform.getPointFromFeet(getLeftVerticalHashPosition(), yardLine * 3.0f);
        PointF rightHash = transform.getPointFromFeet(getRightVerticalHashPosition(), yardLine * 3.0f);

        c.drawLine(leftHash.x, leftHash.y - hashLengthY * 0.5f, leftHash.x, leftHash.y + hashLengthY * 0.5f, _yardLinePaint);
        c.drawLine(rightHash.x, rightHash.y - hashLengthY * 0.5f, rightHash.x, rightHash.y + hashLengthY * 0.5f, _yardLinePaint);
    }

    private void drawHashMark(Canvas c, FieldTransform transform, int yardLine) {

        float hashLengthX = transform.getPointFromFeet(2.0f, 0.0f).x;

        float leftSideline = transform.getPointFromFeet(getLeftSideLineHashPosition(), 0.0f).x;
        float rightSideline = transform.getPointFromFeet(getRightSideLineHashPosition(), 0.0f).x;
        float yardPoint = transform.getYardPoint(yardLine);

        c.drawLine(leftSideline, yardPoint, leftSideline + hashLengthX, yardPoint, _yardLinePaint);
        c.drawLine(rightSideline - hashLengthX, yardPoint, rightSideline, yardPoint, _yardLinePaint);

        float leftHash = transform.getPointFromFeet(getLeftHashPosition(), 0.0f).x;
        float rightHash = transform.getPointFromFeet(getRightHashPosition(), 0.0f).x;
        c.drawLine(leftHash, yardPoint, leftHash + hashLengthX, yardPoint, _yardLinePaint);
        c.drawLine(rightHash - hashLengthX, yardPoint, rightHash, yardPoint, _yardLinePaint);
    }

    private float getLeftSideLineHashPosition() {
        return 0.5f;
    }

    private float getRightSideLineHashPosition() {
        return getFieldWidth() - getLeftSideLineHashPosition();
    }

    private float getLeftHashPosition() {
        return 70.75f;
    }

    private float getRightHashPosition() {
        return getFieldWidth() - getLeftHashPosition();
    }

    private float getLeftVerticalHashPosition() {
        return getLeftHashPosition() + 2.0f;
    }

    private float getRightVerticalHashPosition() {
        return getFieldWidth() - getLeftVerticalHashPosition();
    }
}
