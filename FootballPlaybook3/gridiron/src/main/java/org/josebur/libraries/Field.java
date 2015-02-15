package org.josebur.libraries;

import org.josebur.libraries.math.Point;

public class Field {

    PlayFieldProperties _play;
    FieldMeasurements _measurements;

    public Field(PlayFieldProperties play) {
        _play = play;
        _measurements = new NflFieldMeasurements();
    }

    public void draw(FieldPainter painter, PlayTransform transform) {
        painter.drawGrass();

        drawSidelines(painter, transform);
        drawEndlines(painter, transform);
        drawYardLines(painter, transform);
    }

    private void drawSidelines(FieldPainter painter, PlayTransform transform) {
        float top = transform.feetToPixel(0, 0).y();
        float bottom = transform.feetToPixel(0, _measurements.FullFieldLength()).y();

        // Left Side
        float left = transform.feetToPixel(0, 0).x();
        float right = transform.feetToPixel(_measurements.BorderSize(), 0).x();
        painter.drawSideline(left, top, right, bottom);

        // Right Side
        left = transform.feetToPixel(_measurements.FullFieldWidth() - _measurements.BorderSize(), 0).x();
        right = transform.feetToPixel(_measurements.FullFieldWidth(), 0).x();
        painter.drawSideline(left, top, right, bottom);
    }

    private void drawEndlines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixel(_measurements.BorderSize(), 0).x();
        float right = transform.feetToPixel(_measurements.FullFieldWidth() - _measurements.BorderSize(), 0).x();

        // North Side
        float top = transform.feetToPixel(0, 0).y();
        float bottom = transform.feetToPixel(0, _measurements.BorderSize()).y();
        painter.drawEndline(left, top, right, bottom);

        //South Side
        top = transform.feetToPixel(0, _measurements.FullFieldLength() - _measurements.BorderSize()).y();
        bottom = transform.feetToPixel(0, _measurements.FullFieldLength()).y();
        painter.drawEndline(left, top, right, bottom);
    }

    private void drawYardLines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixel(_measurements.BorderSize(), 0).x();
        float right = transform.feetToPixel(_measurements.BorderSize() + _measurements.Width(), 0).x();

        for (int yard = 0; yard <= 100; yard += 5) {
            float fieldPosition = transform.feetToPixel(0, _measurements.getFullFieldFootLine(yard)).y();
            painter.drawYardLine(left, right, fieldPosition);
        }
    }
}
