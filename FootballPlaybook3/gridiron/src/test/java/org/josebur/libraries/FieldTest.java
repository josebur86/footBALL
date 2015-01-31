package org.josebur.libraries;

import org.josebur.libraries.helpers.FakePlayFieldProperties;
import org.josebur.libraries.helpers.FakeViewPort;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FieldTest {

    @Test
    public void draw_bigScreenSmallField_FieldBorderIsDrawn() {
        ViewPort view = new FakeViewPort(1000, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(1000, 500);
        PlayTransform transform = new PlayTransform(play, view);
        Field field = new Field(play);

        FieldPainter mockPainter = mock(FieldPainter.class);
        field.draw(mockPainter, transform);

        verify(mockPainter).drawSideline(0.f, 250.f, 0.f, 750.f);
        verify(mockPainter).drawSideline(1000.f, 250.f, 1000.f, 750.f);

        verify(mockPainter).drawEndline(0.f, 250.f, 1000.f, 250.f);
        verify(mockPainter).drawEndline(0.f, 750.f, 1000.f, 750.f);
    }
}