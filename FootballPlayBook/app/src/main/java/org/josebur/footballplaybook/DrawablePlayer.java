package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class DrawablePlayer {
    private PointF _pixelPosition;
    private boolean _selected;

    public static float kPlayerRadius = 25;

    public DrawablePlayer(IPlayer player, FieldTransform fieldTransform) {
        _pixelPosition = fieldTransform.getPointFromFeet(player.getPosition());
        _selected = false;
    }

    public DrawablePlayer setSelected(boolean s) {
        _selected = s;
        return this;
    }

    public void draw(Canvas c) {
        float playerRingRadius = kPlayerRadius - 5;

        Paint playerPaint = new Paint();
        playerPaint.setColor(_selected ? Color.RED : Color.WHITE);

        Paint playerRingPaint = new Paint();
        playerRingPaint.setStyle(Paint.Style.STROKE);
        playerRingPaint.setColor(_selected ? Color.WHITE : Color.RED);
        playerRingPaint.setStrokeWidth(3);
        playerRingPaint.setAlpha(127);

        c.drawCircle(_pixelPosition.x, _pixelPosition.y, kPlayerRadius, playerPaint);
        c.drawCircle(_pixelPosition.x, _pixelPosition.y, playerRingRadius, playerRingPaint);
    }

    public HitTarget hitTest(float xPixel, float yPixel) {
        RectF r = new RectF(_pixelPosition.x - kPlayerRadius,
                _pixelPosition.y - kPlayerRadius,
                _pixelPosition.x + kPlayerRadius,
                _pixelPosition.y + kPlayerRadius);
        return r.contains(xPixel, yPixel) ? HitTarget.Player : HitTarget.None;
    }
}
