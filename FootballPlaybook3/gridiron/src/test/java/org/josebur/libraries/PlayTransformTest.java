package org.josebur.libraries;

import org.josebur.libraries.helpers.FakePlayFieldProperties;
import org.josebur.libraries.helpers.FakeViewPort;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayTransformTest {

    @Test
    public void pixelToFeet_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        float feetX = transform.pixelToFeetX(0.f);
        float feetY = transform.pixelToFeetY(0.f);
        assertEquals(0.f, feetX, 0.001);
        assertEquals(100.f, feetY, 0.001);

        // Bottom Right
        feetX = transform.pixelToFeetX(100.f);
        feetY = transform.pixelToFeetY(100.f);
        assertEquals(172.f, feetX, 0.001);
        assertEquals(272.f, feetY, 0.001);

        // Middle Center
        feetX = transform.pixelToFeetX(50.f);
        feetY = transform.pixelToFeetY(50.f);
        assertEquals(86.f, feetX, 0.001);
        assertEquals(186.f, feetY, 0.001);
    }

    @Test
    public void feetToPixel_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        float pixelX = transform.feetToPixelX(0.f);
        float pixelY = transform.feetToPixelY(0.f);
        assertEquals(0.f, pixelX, 0.001);
        assertEquals(-58.139, pixelY, 0.001);

        // Bottom Right
        pixelX = transform.feetToPixelX(100.f);
        pixelY = transform.feetToPixelY(100.f);
        assertEquals(58.139, pixelX, 0.001);
        assertEquals(0.f, pixelY, 0.001);

        // Middle Center
        pixelX = transform.feetToPixelX(50.f);
        pixelY = transform.feetToPixelY(50.f);
        assertEquals(29.069f, pixelX, 0.001);
        assertEquals(-29.069, pixelY, 0.001);
    }

    @Test
    public void pixelToFeet_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float feetX = transform.pixelToFeetX(0.f);
        float feetY = transform.pixelToFeetY(0.f);
        assertEquals(0.f, feetX, 0.001);
        assertEquals(160.2f, feetY, 0.001);

        // Lower Right Pixel
        feetX = transform.pixelToFeetX(1000);
        feetY = transform.pixelToFeetY(300);
        assertEquals(172.f, feetX, 0.001);
        assertEquals(211.8f, feetY, 0.001);

        // Middle Center Pixel
        feetX = transform.pixelToFeetX(500);
        feetY = transform.pixelToFeetY(150);
        assertEquals(play.ballSpotFeetX(), feetX, 0.001);
        assertEquals(play.ballSpotFeetY(), feetY, 0.001);
    }

    @Test
    public void feetToPixel_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Field Corner
        float pixelX = transform.feetToPixelX(0.f);
        float pixelY = transform.feetToPixelY(0.f);
        assertEquals(0.f, pixelX, 0.001);
        assertEquals(-931.395f, pixelY, 0.001);

        // Lower Right Field Corner
        pixelX = transform.feetToPixelX(172);
        pixelY = transform.feetToPixelY(372);
        assertEquals(1000.f, pixelX, 0.001);
        assertEquals(1231.395f, pixelY, 0.001);

        // Middle Center Of The Field
        pixelX = transform.feetToPixelX(play.ballSpotFeetX());
        pixelY = transform.feetToPixelY(play.ballSpotFeetY());
        assertEquals(500, pixelX, 0.001);
        assertEquals(150, pixelY, 0.001);
    }

    @Test
    public void pixelToFeet_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float feetX = transform.pixelToFeetX(0.f);
        float feetY = transform.pixelToFeetY(0.f);
        assertEquals(0.f, feetX, 0.001);
        assertEquals(14.f, feetY, 0.001);

        // Lower Right Pixel
        feetX = transform.pixelToFeetX(500);
        feetY = transform.pixelToFeetY(1000);
        assertEquals(172.f, feetX, 0.001);
        assertEquals(358.f, feetY, 0.001);

        // Middle Center Pixel
        feetX = transform.pixelToFeetX(250);
        feetY = transform.pixelToFeetY(500);
        assertEquals(play.ballSpotFeetX(), feetX, 0.001);
        assertEquals(play.ballSpotFeetY(), feetY, 0.001);
    }

    @Test
    public void feetToPixel_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float pixelX = transform.feetToPixelX(0.f);
        float pixelY = transform.feetToPixelY(50.f);
        assertEquals(0.f, pixelX, 0.001);
        assertEquals(104.651f, pixelY, 0.001);

        // Lower Right Pixel
        pixelX = transform.feetToPixelX(100);
        pixelY = transform.feetToPixelY(250);
        assertEquals(290.697, pixelX, 0.001);
        assertEquals(686.046, pixelY, 0.001);

        // Middle Center Pixel
        pixelX = transform.feetToPixelX(play.ballSpotFeetX());
        pixelY = transform.feetToPixelY(play.ballSpotFeetY());
        assertEquals(250.f, pixelX, 0.001);
        assertEquals(500.f, pixelY, 0.001);
    }

    @Test
    public void pixelToFeet_bigScreenSmallField_ValuesAreCorrect() {
        ViewPort port = new FakeViewPort(1000, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(1000.f, 500.f);

        PlayTransform transform = new PlayTransform(play, port);

        float feetY = transform.pixelToFeetY(0);
        assertEquals(100.f, feetY, 0.001);

        feetY = transform.pixelToFeetY(1000);
        assertEquals(272.f, feetY, 0.001);

        feetY = transform.pixelToFeetY(500);
        assertEquals(186.f, feetY, 0.001);
    }

    @Test
    public void widthFromFeet_nflSizeField_widthMatchesViewWidth() {
        ViewPort portraitView = new FakeViewPort(1080, 1920);

        float playWidth = new NflFieldMeasurements().FullFieldWidth2();
        float playLength = new NflFieldMeasurements().FullFieldLength2();
        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playLength);

        PlayTransform transform = new PlayTransform(play, portraitView);

        assertEquals(1080.f, transform.widthFromFeet(playWidth), 0.001);
        assertEquals(540.f, transform.widthFromFeet(play.ballSpotFeetX()), 0.001);
    }

    @Test
    public void lengthFromFeet_nflSizeField_lengthMatchesViewHeight() {
        ViewPort portraitView = new FakeViewPort(1080, 1920);

        float playWidth = new NflFieldMeasurements().FullFieldWidth2();
        float playLength = new NflFieldMeasurements().FullFieldLength2();
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

        assertEquals(540.f, transform.feetToPixelX(play.ballSpotFeetX()), 0.001);
        assertEquals(960.f, transform.feetToPixelY(play.ballSpotFeetY()), 0.001);

        // Upper Left Field Corner
        assertEquals(0.f, transform.feetToPixelX(0), 0.001);
        assertEquals(-207.90f, transform.feetToPixelY(0), 0.01);

        // Lower Right Field Corner
        assertEquals(1080.f, transform.feetToPixelX(playWidth), 0.01);
        assertEquals(2127.90f, transform.feetToPixelY(playHeight), 0.01);
    }

    @Test
    public void feetToPixel_nflSizePlayFieldAndNexus5ScreenResolution_ballSpotIsAtCenterPixel() {
        ViewPort nexus5PortraitView = new FakeViewPort(1080, 1920);

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, nexus5PortraitView);

        assertEquals(540.f, transform.feetToPixelX(play.ballSpotFeetX()), 0.001);
        assertEquals(960.f, transform.feetToPixelY(play.ballSpotFeetY()), 0.001);
    }

    @Test
    public void viewCenter_nflSizePlayFieldNexus5ScreenResolution_viewCenterIsAtTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);

        assertEquals(86.f, transform.viewCenterInFeetX(), 0.001);
        assertEquals(186.f, transform.viewCenterInFeetY(), 0.001);
    }

    @Test
    public void pan_panLeft100px_viewCenterIsLeftOfTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenterInFeetX(), 0.001);
        assertEquals(186.f, transform.viewCenterInFeetY(), 0.001);

        transform.pan(-100, 0);
        assertEquals(70.07407407, transform.viewCenterInFeetX(), 0.001);
    }

    @Test
    public void pan_panRight100px_viewCenterIsRightOfTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenterInFeetX(), 0.001);
        assertEquals(186.f, transform.viewCenterInFeetY(), 0.001);

        transform.pan(100, 0);
        assertEquals(101.9259259, transform.viewCenterInFeetX(), 0.001);
    }

    @Test
    public void pan_panUp100px_viewCenterIsAboveTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenterInFeetX(), 0.001);
        assertEquals(186.f, transform.viewCenterInFeetY(), 0.001);

        transform.pan(0, -100);
        assertEquals(170.0740741, transform.viewCenterInFeetY(), 0.001);
    }

    @Test
    public void pan_panDown100px_viewCenterIsBelowTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.viewCenterInFeetX(), 0.001);
        assertEquals(186.f, transform.viewCenterInFeetY(), 0.001);

        transform.pan(0, 100);
        assertEquals(201.9259259, transform.viewCenterInFeetY(), 0.001);
    }

    @Test
    @Ignore
    public void zoom_double_viewCenterIsAtBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(86.f, transform.pixelToFeetX(540), 0.001);
        assertEquals(172.f, transform.pixelToFeetX(1080), 0.001);

        transform.zoom(2);
        assertEquals(86.f, transform.pixelToFeetX(540), 0.001);
        assertEquals(129.0622f, transform.pixelToFeetX(1080), 0.001);
    }
}