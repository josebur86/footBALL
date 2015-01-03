package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.PointF;

public interface IPlayer {
    String label();

    boolean isSelected();

    void moveTo(PointF positionFeet);

    void draw(Canvas c, FieldTransform fieldTransform);

    boolean hitTest(float xPixel, float yPixel, FieldTransform fieldTransform);
}
