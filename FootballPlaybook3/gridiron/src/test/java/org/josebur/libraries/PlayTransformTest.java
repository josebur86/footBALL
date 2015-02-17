package org.josebur.libraries;

import org.josebur.libraries.helpers.FakePlayFieldProperties;
import org.josebur.libraries.helpers.FakeViewPort;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayTransformTest {

    @Test
    public void pixelToFeet_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        Position feet = transform.pixelToFeet(0, 0);
        assertEquals(0.f, feet.feetX(), 0.001);
        assertEquals(100.f, feet.feetY(), 0.001);

        // Bottom Right
        feet = transform.pixelToFeet(100, 100);
        assertEquals(172.f, feet.feetX(), 0.001);
        assertEquals(272.f, feet.feetY(), 0.001);

        // Middle Center
        feet = transform.pixelToFeet(50, 50);
        assertEquals(86.f, feet.feetX(), 0.001);
        assertEquals(186.f, feet.feetY(), 0.001);
    }

    @Test
    public void feetToPixel_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        Pixel pixel = transform.feetToPixel(new Position(0, 0));
        assertEquals(0.f, pixel.x(), 0.001);
        assertEquals(-58.139, pixel.y(), 0.001);

        // Bottom Right
        pixel = transform.feetToPixel(new Position(100, 100));
        assertEquals(58.139, pixel.x(), 0.001);
        assertEquals(0.f, pixel.y(), 0.001);

        // Middle Center
        pixel = transform.feetToPixel(new Position(50, 50));
        assertEquals(29.069f, pixel.x(), 0.001);
        assertEquals(-29.069, pixel.y(), 0.001);
    }

    @Test
    public void pixelToFeet_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        Position feet = transform.pixelToFeet(0, 0);
        assertEquals(0.f, feet.feetX(), 0.001);
        assertEquals(160.2f, feet.feetY(), 0.001);

        // Lower Right Pixel
        feet = transform.pixelToFeet(1000, 300);
        assertEquals(172.f, feet.feetX(), 0.001);
        assertEquals(211.8f, feet.feetY(), 0.001);

        // Middle Center Pixel
        feet = transform.pixelToFeet(500, 150);
        assertEquals(play.ballSpotFeetX(), feet.feetX(), 0.001);
        assertEquals(play.ballSpotFeetY(), feet.feetY(), 0.001);
    }

    @Test
    public void feetToPixel_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Field Corner
        Pixel pixel = transform.feetToPixel(new Position(0, 0));
        assertEquals(0.f, pixel.x(), 0.001);
        assertEquals(-931.395f, pixel.y(), 0.001);

        // Lower Right Field Corner
        pixel = transform.feetToPixel(new Position(172, 372));
        assertEquals(1000.f, pixel.x(), 0.001);
        assertEquals(1231.395f, pixel.y(), 0.001);

        // Middle Center Of The Field
        pixel = transform.feetToPixel(play.ballSpot());
        assertEquals(500, pixel.x(), 0.001);
        assertEquals(150, pixel.y(), 0.001);
    }

    @Test
    public void pixelToFeet_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        Position feet  = transform.pixelToFeet(0, 0);
        assertEquals(0.f, feet.feetX(), 0.001);
        assertEquals(14.f, feet.feetY(), 0.001);

        // Lower Right Pixel
        feet = transform.pixelToFeet(500, 1000);
        assertEquals(172.f, feet.feetX(), 0.001);
        assertEquals(358.f, feet.feetY(), 0.001);

        // Middle Center Pixel
        feet = transform.pixelToFeet(250, 500);
        assertEquals(play.ballSpotFeetX(), feet.feetX(), 0.001);
        assertEquals(play.ballSpotFeetY(), feet.feetY(), 0.001);
    }

    @Test
    public void feetToPixel_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        Pixel pixel = transform.feetToPixel(new Position(0, 50));
        assertEquals(0.f, pixel.x(), 0.001);
        assertEquals(104.651f, pixel.y(), 0.001);

        // Lower Right Pixel
        pixel = transform.feetToPixel(new Position(100, 250));
        assertEquals(290.697, pixel.x(), 0.001);
        assertEquals(686.046, pixel.y(), 0.001);

        // Middle Center Pixel
        pixel = transform.feetToPixel(play.ballSpot());
        assertEquals(250.f, pixel.x(), 0.001);
        assertEquals(500.f, pixel.y(), 0.001);
    }

    @Test
    public void pixelToFeet_bigScreenSmallField_ValuesAreCorrect() {
        ViewPort port = new FakeViewPort(1000, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(1000.f, 500.f);

        PlayTransform transform = new PlayTransform(play, port);

        float feetY = transform.pixelToFeet(0, 0).feetY();
        assertEquals(100.f, feetY, 0.001);

        feetY = transform.pixelToFeet(0, 1000).feetY();
        assertEquals(272.f, feetY, 0.001);

        feetY = transform.pixelToFeet(0, 500).feetY();
        assertEquals(186.f, feetY, 0.001);
    }

    @Test
    public void widthFromFeet_nflSizeField_widthMatchesViewWidth() {
        ViewPort portraitView = new FakeViewPort(1080, 1920);

        float playWidth = new NflFieldMeasurements().FullFieldWidth();
        float playLength = new NflFieldMeasurements().FullFieldLength();
        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playLength);

        PlayTransform transform = new PlayTransform(play, portraitView);

        assertEquals(1080.f, transform.widthFromFeet(playWidth), 0.001);
        assertEquals(540.f, transform.widthFromFeet(play.ballSpotFeetX()), 0.001);
    }

    @Test
    public void lengthFromFeet_nflSizeField_lengthMatchesViewHeight() {
        ViewPort portraitView = new FakeViewPort(1080, 1920);

        float playWidth = new NflFieldMeasurements().FullFieldWidth();
        float playLength = new NflFieldMeasurements().FullFieldLength();
        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playLength);

        PlayTransform transform = new PlayTransform(play, portraitView);


        assertEquals(2335.81f, transform.lengthFromFeet(playLength), 0.01);
        assertEquals(1167.90f, transform.lengthFromFeet(play.ballSpotFeetY()), 0.01);
    }

    @Test
    public void feetToPixel_nflSizePlayFieldPlusSideLineAndNexus5ScreenResolution_ballSpotIsAtCenterPixel() {
        ViewPort nexus5PortraitView = new FakeViewPort(1080, 1920);

        float playWidth = 172.f;
        float playHeight = 372.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, nexus5PortraitView);

        Pixel pixel = transform.feetToPixel(play.ballSpot());
        assertEquals(540.f, pixel.x(), 0.001);
        assertEquals(960.f, pixel.y(), 0.001);

        // Upper Left Field Corner
        pixel = transform.feetToPixel(new Position(0, 0));
        assertEquals(0.f, pixel.x(), 0.001);
        assertEquals(-207.90f, pixel.y(), 0.01);

        // Lower Right Field Corner
        pixel = transform.feetToPixel(new Position(playWidth, playHeight));
        assertEquals(1080.f, pixel.x(), 0.01);
        assertEquals(2127.90f, pixel.y(), 0.01);
    }

    @Test
    public void feetToPixel_nflSizePlayFieldAndNexus5ScreenResolution_ballSpotIsAtCenterPixel() {
        ViewPort nexus5PortraitView = new FakeViewPort(1080, 1920);

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, nexus5PortraitView);

        // FIXME: PlayFieldProperties.ballSpot() should return a Position object.
        Position ballSpot = new Position(play.ballSpotFeetX(), play.ballSpotFeetY());
        Pixel pixel = transform.feetToPixel(ballSpot);
        assertEquals(540.f, pixel.x(), 0.001);
        assertEquals(960.f, pixel.y(), 0.001);
    }

    @Test
    public void viewCenter_nflSizePlayFieldNexus5ScreenResolution_viewCenterIsAtTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);

        assertEquals(86.f, transform.viewCenter().feetX(), 0.001);
        assertEquals(186.f, transform.viewCenter().feetY(), 0.001);
    }

    @Test
    public void pan_panLeft100px_viewCenterIsLeftOfTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenter().feetX(), 0.001);
        assertEquals(186.f, transform.viewCenter().feetY(), 0.001);

        transform.pan(-100, 0);
        assertEquals(70.07407407, transform.viewCenter().feetX(), 0.001);
    }

    @Test
    public void pan_panRight100px_viewCenterIsRightOfTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenter().feetX(), 0.001);
        assertEquals(186.f, transform.viewCenter().feetY(), 0.001);

        transform.pan(100, 0);
        assertEquals(101.9259259, transform.viewCenter().feetX(), 0.001);
    }

    @Test
    public void pan_panUp100px_viewCenterIsAboveTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenter().feetX(), 0.001);
        assertEquals(186.f, transform.viewCenter().feetY(), 0.001);

        transform.pan(0, -100);
        assertEquals(170.0740741, transform.viewCenter().feetY(), 0.001);
    }

    @Test
    public void pan_panDown100px_viewCenterIsBelowTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenter().feetX(), 0.001);
        assertEquals(186.f, transform.viewCenter().feetY(), 0.001);

        transform.pan(0, 100);
        assertEquals(201.9259259, transform.viewCenter().feetY(), 0.001);
    }

    private class FakeFieldMeasurements implements FieldMeasurements {

        private float _width;
        private float _length;

        public FakeFieldMeasurements(float width, float length) {
            _width = width;
            _length = length;
        }

        @Override
        public float Width() {
            return _width;
        }

        @Override
        public float Length() {
            return _length;
        }

        @Override
        public float FullFieldWidth() {
            return _width;
        }

        @Override
        public float FullFieldLength() {
            return _length;
        }

        @Override
        public float EndZoneLength() {
            return 0;
        }

        @Override
        public float BorderSize() {
            return 0;
        }

        @Override
        public int getFullFieldFootLine(int yardLine) {
            return yardLine;
        }
    }

    @Test
    public void zoom_double_viewCenterIsAtBallSpot() {
        int width = 100;
        int length = 100;
        ViewPort port = new FakeViewPort(width, length); // TODO: create a fake viewport factory.
        PlayFieldProperties play = new FakePlayFieldProperties(width, length);
        PlayTransform transform = new PlayTransform(play, port, new FakeFieldMeasurements(width, length));
        assertEquals(50.f, transform.pixelToFeet(50, 0).feetX(), 0.001);
        assertEquals(100.f, transform.pixelToFeet(100, 0).feetX(), 0.001);

        transform.zoom(2);
        assertEquals(50.f, transform.pixelToFeet(50, 0).feetX(), 0.001);
        assertEquals(75.f, transform.pixelToFeet(100, 0).feetX(), 0.001);
    }

    @Test
    public void panAndZoom_panDown25pxThenDoubleZoom_viewCenterIsPreserved() {
        int width = 100;
        int length = 100;
        ViewPort port = new FakeViewPort(width, length); // TODO: create a fake viewport factory.
        PlayFieldProperties play = new FakePlayFieldProperties(width, length);
        PlayTransform transform = new PlayTransform(play, port, new FakeFieldMeasurements(width, length));
        assertEquals(50.f, transform.pixelToFeet(50, 0).feetX(), 0.001);
        assertEquals(100.f, transform.pixelToFeet(100, 0).feetX(), 0.001);

        transform.pan(0, 25);
        assertEquals(125.f, transform.pixelToFeet(0, 100).feetY(), 0.01);
        assertEquals(75.f, transform.pixelToFeet(0, 50).feetY(), 0.01);

        transform.zoom(2);
        assertEquals(100.f, transform.pixelToFeet(0, 100).feetY(), 0.001);
    }
}