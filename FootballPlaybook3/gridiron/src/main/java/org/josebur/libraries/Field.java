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
        final float top = 0;
        final float bottom = _measurements.FullFieldLength();

        // Left Side
        Pixel topLeft = transform.feetToPixel(new Position(0, top));
        Pixel bottomRight = transform.feetToPixel(new Position(_measurements.BorderSize(), bottom));
        painter.drawSideline(topLeft, bottomRight);

        // Right Side
        topLeft = transform.feetToPixel(new Position(_measurements.FullFieldWidth() - _measurements.BorderSize(), top));
        bottomRight = transform.feetToPixel(new Position(_measurements.FullFieldWidth(), bottom));
        painter.drawSideline(topLeft, bottomRight);
    }

    private void drawEndlines(FieldPainter painter, PlayTransform transform) {
        final float left = _measurements.BorderSize();
        final float right = _measurements.FullFieldWidth() - _measurements.BorderSize();

        // North Side
        Pixel topLeft = transform.feetToPixel(new Position(left, 0));
        Pixel bottomRight = transform.feetToPixel(new Position(right, _measurements.BorderSize()));
        painter.drawEndline(topLeft, bottomRight);

        //South Side
        topLeft = transform.feetToPixel(new Position(left, _measurements.FullFieldLength() - _measurements.BorderSize()));
        bottomRight = transform.feetToPixel(new Position(right, _measurements.FullFieldLength()));
        painter.drawEndline(topLeft, bottomRight);
    }

    private void drawYardLines(FieldPainter painter, PlayTransform transform) {
        final float left = _measurements.BorderSize();
        final float right = _measurements.BorderSize() + _measurements.Width();

        for (int yard = 0; yard <= 100; yard += 5) {
            float fieldPosition = _measurements.getFullFieldFootLine(yard);
            Pixel leftYard = transform.feetToPixel(new Position(left, fieldPosition));
            Pixel rightYard = transform.feetToPixel(new Position(right, fieldPosition));
            painter.drawYardLine(leftYard.x(), rightYard.x(), leftYard.y());
        }
    }
}
