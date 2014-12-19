package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Player {

    private PointF _fieldPositionFeet;

    public static float kPlayerRadius = 25;

    public Player(PointF fieldPositionFeet)
    {
        _fieldPositionFeet = fieldPositionFeet;
    }

    public void draw(Canvas c, FieldTransform fieldTransform)
    {
        new DrawablePlayer(this, fieldTransform).draw(c);
    }

    private class DrawablePlayer
    {
        private PointF _pixelPosition;

        public DrawablePlayer(Player player, FieldTransform fieldTransform)
        {
            _pixelPosition = fieldTransform.getPointFromFeet(player._fieldPositionFeet);
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
