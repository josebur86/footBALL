package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class Player implements IPlayer {

    private String _label;
    private PointF _fieldPositionFeet;

    public static float kPlayerRadius = 25;

    public Player(String label, float xFeet, float yFeet)
    {
        _label = label;
        _fieldPositionFeet = new PointF(xFeet, yFeet);
    }

    @Override
    public String label() {
        return _label;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public void moveTo(PointF positionFeet) {
        _fieldPositionFeet = positionFeet;
    }

    public void move(float deltaXFeet, float deltaYFeet) {
        _fieldPositionFeet.x += deltaXFeet;
        _fieldPositionFeet.y += deltaYFeet;
    }

    public float feetX() {
        return _fieldPositionFeet.x;
    }

    public float feetY() {
        return _fieldPositionFeet.y;
    }

    @Override
    public void draw(Canvas c, FieldTransform fieldTransform) {
        new DrawablePlayer(this, fieldTransform).setSelected(isSelected()).draw(c);
    }

    @Override
    public boolean hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        return new DrawablePlayer(this, fieldTransform).hitTest(xPixel, yPixel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!_label.equals(player._label)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _label.hashCode();
    }

    private class DrawablePlayer {
        private PointF _pixelPosition;
        private FieldTransform _fieldTransform;
        private boolean _selected;

        public DrawablePlayer(Player player, FieldTransform fieldTransform) {
            _fieldTransform = fieldTransform;
            _pixelPosition = _fieldTransform.getPointFromFeet(player._fieldPositionFeet);
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

        public boolean hitTest(float xPixel, float yPixel) {
            RectF r = new RectF(_pixelPosition.x - kPlayerRadius,
                                _pixelPosition.y - kPlayerRadius,
                                _pixelPosition.x + kPlayerRadius,
                                _pixelPosition.y + kPlayerRadius);
            return r.contains(xPixel, yPixel);
        }
    }
}
