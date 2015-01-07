package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.PointF;

public interface IPlayer {

    String label();
    PointF getPosition();
    boolean isSelected();

    void moveTo(PointF positionFeet);
    void move(float deltaXFeet, float deltaYFeet);

    void draw(Canvas c, FieldTransform fieldTransform);

    HitTarget hitTest(float xPixel, float yPixel, FieldTransform fieldTransform);
}
