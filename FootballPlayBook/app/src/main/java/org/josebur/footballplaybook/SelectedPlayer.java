package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.PointF;

public class SelectedPlayer implements IPlayer {

    private IPlayer _player;

    public SelectedPlayer(IPlayer player) {
        _player = player;
    }

    IPlayer getInnerPlayer() {
        return _player;
    }

    @Override
    public String label() {
        return _player.label();
    }

    @Override
    public PointF getPosition() {
        return _player.getPosition();
    }

    @Override
    public boolean isSelected() {
        return true;
    }

    @Override
    public void moveTo(PointF positionFeet) {
        _player.moveTo(positionFeet);
    }

    @Override
    public void draw(Canvas c, FieldTransform fieldTransform) {
        new DrawablePlayer(this, fieldTransform).setSelected(isSelected()).draw(c);
    }

    @Override
    public boolean hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        return _player.hitTest(xPixel, yPixel, fieldTransform);
    }
}
