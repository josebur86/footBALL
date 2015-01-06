package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;

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
    public void move(float deltaXFeet, float deltaYFeet) {
        _player.move(deltaXFeet, deltaYFeet);
    }

    @Override
    public void draw(Canvas c, FieldTransform fieldTransform) {
        new DrawablePlayer(this, fieldTransform).setSelected(isSelected()).draw(c);


        // Experimental drag icon.
        Shape s = new OvalShape();
        s.resize(120.f, 120.f); // TODO: these are magic numbers ATM.
        ShapeDrawable drawable = new ShapeDrawable(s);
        drawable.getPaint().setColor(Color.RED);
        drawable.setBounds(getDragBounds(fieldTransform));
        drawable.draw(c);
    }

    @Override
    public boolean hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        return _player.hitTest(xPixel, yPixel, fieldTransform);
    }

    @Override
    public boolean dragHitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        return getDragBounds(fieldTransform).contains((int)xPixel, (int)yPixel);
    }

    private Rect getDragBounds(FieldTransform fieldTransform) {
        PointF p = fieldTransform.getPointFromFeet(getPosition());
        Rect r = new Rect();
        r.left = (int)p.x - 60;
        r.right = (int)p.x + 60;
        r.top = (int)p.y - 250 - 60;
        r.bottom = (int)p.y - 250 + 60;

        return r;
    }
}
