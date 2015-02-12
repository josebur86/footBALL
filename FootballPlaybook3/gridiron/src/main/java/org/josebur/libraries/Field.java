package org.josebur.libraries;

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
        float top = transform.feetToPixelY(0);
        float bottom = transform.feetToPixelY(_measurements.FullFieldLength2());

        // Left Side
        float left = transform.feetToPixelX(0);
        float right = transform.feetToPixelX(_measurements.BorderSize());
        painter.drawSideline(left, top, right, bottom);

        // Right Side
        left = transform.feetToPixelX(_measurements.FullFieldWidth2() - _measurements.BorderSize());
        right = transform.feetToPixelX(_measurements.FullFieldWidth2());
        painter.drawSideline(left, top, right, bottom);
    }

    private void drawEndlines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(_measurements.BorderSize());
        float right = transform.feetToPixelX(_measurements.FullFieldWidth2() - _measurements.BorderSize());

        // North Side
        float top = transform.feetToPixelY(0);
        float bottom = transform.feetToPixelY(_measurements.BorderSize());
        painter.drawEndline(left, top, right, bottom);

        //South Side
        top = transform.feetToPixelY(_measurements.FullFieldLength2() - _measurements.BorderSize());
        bottom = transform.feetToPixelY(_measurements.FullFieldLength2());
        painter.drawEndline(left, top, right, bottom);
    }

    private void drawYardLines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(_measurements.BorderSize());
        float right = transform.feetToPixelX(_measurements.BorderSize() + _measurements.Width());

        for (int yard = 0; yard <= 100; yard += 5) {
            float fieldPosition = transform.feetToPixelY(_measurements.getFullFieldFootLine2(yard));
            painter.drawYardLine(left, right, fieldPosition);
        }
    }
}
