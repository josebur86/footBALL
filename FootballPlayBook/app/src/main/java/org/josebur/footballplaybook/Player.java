package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Player {

    private PointF _fieldPosition;

    public static float kPlayerRadius = 25;

    public Player(PointF fieldPosition)
    {
        _fieldPosition = fieldPosition;
    }

    public void draw(Canvas c, FieldTransform fieldTransform)
    {
        new DrawablePlayer(fieldTransform, _fieldPosition).draw(c);
    }

    private class DrawablePlayer
    {
        private PointF _pixelPosition;

        public DrawablePlayer(FieldTransform fieldTransform, PointF fieldPosition)
        {
            _pixelPosition = fieldTransform.getPointFromFeet(fieldPosition.x, fieldPosition.y);
        }

        public void draw(Canvas c)
        {
          float playerRingRadius = kPlayerRadius - 5;

          Paint playerPaint = new Paint();
          playerPaint.setColor(Color.WHITE);

          Paint playerRingPaint = new Paint();
          playerRingPaint.setStyle(Paint.Style.STROKE);
          playerRingPaint.setColor(Color.RED);
          playerRingPaint.setStrokeWidth(3);
          playerRingPaint.setAlpha(127);

          c.drawCircle(_pixelPosition.x, _pixelPosition.y, kPlayerRadius, playerPaint);
          c.drawCircle(_pixelPosition.x, _pixelPosition.y, playerRingRadius, playerRingPaint);
        }
    }
}
