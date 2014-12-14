package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Field {

    private FieldTransform _transform;
    private Paint _yardLinePaint;

    public Field() {
        _yardLinePaint = new Paint();
        _yardLinePaint.setColor(Color.WHITE);
        _yardLinePaint.setAlpha(190);
        _yardLinePaint.setStrokeWidth(5);
    }

    public void setCanvasSize(int width, int length) {
        _transform = new FieldTransform(width, length, getFieldWidth(), getFieldLength());
    }

    public void draw(Canvas c) {
        c.drawRGB(0, 100, 0);

        int totalYards = getFieldLength() / FieldTransform.kFeetPerYard;
        for (int yardLine = 1; yardLine < totalYards; yardLine++) {
            if (yardLine % 5 == 0) {
                drawFiveYardIncrementLine(c, yardLine);
                drawVerticalHashMark(c, yardLine);
            }
            else {
                drawHashMark(c, yardLine);
            }
        }

        // Test Player Color: #0B162A rgb(11, 22, 42)
        Paint playerPaint = new Paint();
        playerPaint.setColor(Color.WHITE);
        Paint playerRingPaint = new Paint();
        playerRingPaint.setStyle(Paint.Style.STROKE);
        playerRingPaint.setColor(Color.RED);
        playerRingPaint.setStrokeWidth(3);
        playerRingPaint.setAlpha(127);


        float playerRadius = 25;
        float playerRingRadius = 20;
        float gap = _transform.xFeetLengthFromPoint(playerRadius * 2);
        PointF center = _transform.getPointFromFeet(getFieldWidth() / 2.0f, 10 * FieldTransform.kFeetPerYard);
        PointF rightGuard = _transform.getPointFromFeet(getFieldWidth() / 2.0f + gap + 1.0f, 10 * FieldTransform.kFeetPerYard);
        PointF rightTackle = _transform.getPointFromFeet(getFieldWidth() / 2.0f + 2.0f * gap + 2.0f, 10 * FieldTransform.kFeetPerYard);

        c.drawCircle(center.x, center.y, playerRadius, playerPaint);
        c.drawCircle(center.x, center.y, playerRingRadius, playerRingPaint);
        c.drawCircle(rightGuard.x, rightGuard.y, playerRadius, playerPaint);
        c.drawCircle(rightGuard.x, rightGuard.y, playerRingRadius, playerRingPaint);
        c.drawCircle(rightTackle.x, rightTackle.y, playerRadius, playerPaint);
        c.drawCircle(rightTackle.x, rightTackle.y, playerRingRadius, playerRingPaint);
    }

    private void drawFiveYardIncrementLine(Canvas c, int yardLine) {
        float yardPoint = _transform.getYardPoint(yardLine);
        float canvasWidth = _transform.getPointFromFeet(getFieldWidth(), 0.0f).x;
        c.drawLine(0, yardPoint, canvasWidth, yardPoint, _yardLinePaint);
    }

    private void drawVerticalHashMark(Canvas c, int yardLine) {
        float hashLengthY = _transform.getPointFromFeet(0.0f, 1.0f).y;
        PointF leftHash = _transform.getPointFromFeet(getLeftVerticalHashPosition(), yardLine * 3.0f);
        PointF rightHash = _transform.getPointFromFeet(getRightVerticalHashPosition(), yardLine * 3.0f);

        c.drawLine(leftHash.x, leftHash.y - hashLengthY * 0.5f, leftHash.x, leftHash.y + hashLengthY * 0.5f, _yardLinePaint);
        c.drawLine(rightHash.x, rightHash.y - hashLengthY * 0.5f, rightHash.x, rightHash.y + hashLengthY * 0.5f, _yardLinePaint);
    }

    private void drawHashMark(Canvas c, int yardLine) {

        float hashLengthX = _transform.getPointFromFeet(2.0f, 0.0f).x;

        float leftSideline = _transform.getPointFromFeet(getLeftSideLineHashPosition(), 0.0f).x;
        float rightSideline = _transform.getPointFromFeet(getRightSideLineHashPosition(), 0.0f).x;
        float yardPoint = _transform.getYardPoint(yardLine);

        c.drawLine(leftSideline, yardPoint, leftSideline + hashLengthX, yardPoint, _yardLinePaint);
        c.drawLine(rightSideline - hashLengthX, yardPoint, rightSideline, yardPoint, _yardLinePaint);

        float leftHash = _transform.getPointFromFeet(getLeftHashPosition(), 0.0f).x;
        float rightHash = _transform.getPointFromFeet(getRightHashPosition(), 0.0f).x;
        c.drawLine(leftHash, yardPoint, leftHash + hashLengthX, yardPoint, _yardLinePaint);
        c.drawLine(rightHash - hashLengthX, yardPoint, rightHash, yardPoint, _yardLinePaint);
    }

    private int getFieldWidth() {
        return 160;
    }

    private int getFieldLength() {
        return 60;
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
