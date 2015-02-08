package org.josebur.libraries;

public class Field {

    PlayFieldProperties _play;

    public Field(PlayFieldProperties play) {
        _play = play;
    }

    public void draw(FieldPainter painter, PlayTransform transform) {
        painter.drawGrass();

        drawSidelines(painter, transform);
        drawEndlines(painter, transform);
        drawYardLines(painter, transform);
    }

    private void drawSidelines(FieldPainter painter, PlayTransform transform) {
        float top = transform.feetToPixelY(0);
        float bottom = transform.feetToPixelY(FieldMeasurements.FullFieldLength());

        // Left Side
        float left = transform.feetToPixelX(0);
        float right = transform.feetToPixelX(FieldMeasurements.BorderSize);
        painter.drawSideline(left, top, right, bottom);

        // Right Side
        left = transform.feetToPixelX(FieldMeasurements.FullFieldWidth() - FieldMeasurements.BorderSize);
        right = transform.feetToPixelX(FieldMeasurements.FullFieldWidth());
        painter.drawSideline(left, top, right, bottom);
    }

    private void drawEndlines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(FieldMeasurements.BorderSize);
        float right = transform.feetToPixelX(FieldMeasurements.FullFieldWidth() - FieldMeasurements.BorderSize);

        // North Side
        float top = transform.feetToPixelY(0);
        float bottom = transform.feetToPixelY(FieldMeasurements.BorderSize);
        painter.drawEndline(left, top, right, bottom);

        //South Side
        top = transform.feetToPixelY(FieldMeasurements.FullFieldLength() - FieldMeasurements.BorderSize);
        bottom = transform.feetToPixelY(FieldMeasurements.FullFieldLength());
        painter.drawEndline(left, top, right, bottom);
    }

    private void drawYardLines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(FieldMeasurements.BorderSize);
        float right = transform.feetToPixelX(FieldMeasurements.BorderSize + FieldMeasurements.Width);

        for (int yard = 0; yard <= 100; yard += 5) {
            float fieldPosition = transform.feetToPixelY(FieldMeasurements.getFullFieldFootLine(yard));
            painter.drawYardLine(left, right, fieldPosition);
        }
    }
}
