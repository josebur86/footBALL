package org.josebur.libraries;

public class Field {

    PlayFieldProperties _play;

    public Field(PlayFieldProperties play) {
        _play = play;
    }

    public void draw(FieldPainter painter, PlayTransform transform) {
        drawSidelines(painter, transform);
        drawEndlines(painter, transform);
    }

    private void drawSidelines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(0);
        float right = transform.feetToPixelX(FieldMeasurements.FullFieldWidth() -
                              FieldMeasurements.BorderSize);

        float top = transform.feetToPixelY(0);
        float bottom = transform.feetToPixelY(FieldMeasurements.FullFieldLength());

        painter.drawSideline(left, top, left, bottom);
        painter.drawSideline(right, top, right, bottom);
    }

    private void drawEndlines(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(FieldMeasurements.BorderSize);
        float right = transform.feetToPixelX(FieldMeasurements.FullFieldWidth() - FieldMeasurements.BorderSize);

        float top = transform.feetToPixelY(0);
        float bottom = transform.feetToPixelY(FieldMeasurements.FullFieldLength() - FieldMeasurements.BorderSize);

        painter.drawEndline(left, top, right, top);
        painter.drawEndline(left, bottom, right, bottom);
    }
}
