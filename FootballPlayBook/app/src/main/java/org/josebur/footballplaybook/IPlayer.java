package org.josebur.footballplaybook;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * Created by josebur on 1/3/15.
 */
public interface IPlayer {
    String label();

    boolean isSelected();

    void setSelected(boolean s);

    void moveTo(PointF positionFeet);

    void draw(Canvas c, FieldTransform fieldTransform);

    boolean hitTest(float xPixel, float yPixel, FieldTransform fieldTransform);
}
