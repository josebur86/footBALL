package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.PointF;

public class Player implements IPlayer {

    private String _label;
    private PointF _fieldPositionFeet;

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
    public PointF getPosition() {
        return _fieldPositionFeet;
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
    public HitTarget hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
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
}
