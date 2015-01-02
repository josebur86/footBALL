package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class Player {

    private String _label;
    private PointF _fieldPositionFeet;

    public static float kPlayerRadius = 25;

    public Player(String label, float xFeet, float yFeet)
    {
        _label = label;
        _fieldPositionFeet = new PointF(xFeet, yFeet);
    }

    public String label() {
        return _label;
    }

    public void draw(Canvas c, FieldTransform fieldTransform) {
        new DrawablePlayer(this, fieldTransform).draw(c);
    }

    public boolean hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        return new DrawablePlayer(this, fieldTransform).hitTest(xPixel, yPixel);
    }

    private class DrawablePlayer {
        private PointF _pixelPosition;
        private FieldTransform _fieldTransform;

        public DrawablePlayer(Player player, FieldTransform fieldTransform) {
            _fieldTransform = fieldTransform;
            _pixelPosition = _fieldTransform.getPointFromFeet(player._fieldPositionFeet);
        }

        public void draw(Canvas c) {
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

        public boolean hitTest(float xPixel, float yPixel) {
            RectF r = new RectF(_pixelPosition.x - kPlayerRadius,
                                _pixelPosition.y - kPlayerRadius,
                                _pixelPosition.x + kPlayerRadius,
                                _pixelPosition.y + kPlayerRadius);
            return r.contains(xPixel, yPixel);
        }
    }
}
