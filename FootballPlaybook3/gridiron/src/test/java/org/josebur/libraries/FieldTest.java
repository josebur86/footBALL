package org.josebur.libraries;

import org.josebur.libraries.helpers.FakePlayFieldProperties;
import org.josebur.libraries.helpers.FakeViewPort;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FieldTest {

    @Test
    public void draw_nflSizeFieldNexus5Resolution() {
        ViewPort view = new FakeViewPort(1080, 1920);
        PlayFieldProperties play = new FakePlayFieldProperties(172, 372);
        PlayTransform transform = new PlayTransform(play, view);
        Field field = new Field(play);

        FieldPainter mockPainter = mock(FieldPainter.class);
        field.draw(mockPainter, transform);

        verify(mockPainter).drawGrass();

        verify(mockPainter).drawSideline(0.f, -207.90698f, 37.67442f, 2127.907f);
        verify(mockPainter).drawSideline(1042.3256f, -207.90698f, 1080.f, 2127.907f);

        verify(mockPainter).drawEndline(37.67442f, -207.90698f, 1042.3256f, -170.23256f);
        verify(mockPainter).drawEndline(37.67442f, 2090.23256f, 1042.3256f, 2127.90698f);

        verify(mockPainter, times(21)).drawYardLine(anyFloat(), anyFloat(), anyFloat());
    }
}