package org.josebur.libraries;

import org.junit.Assert;
import org.junit.Test;

public class NflFieldMeasurementsTest {

    @Test
    public void nflFieldIs160FeetWide() {
        FieldMeasurements fm = new NflFieldMeasurements();
        Assert.assertEquals(160.f, fm.Width(), 0.1);
    }

    @Test
    public void nflFieldIs300FeetLong() {
        FieldMeasurements fm = new NflFieldMeasurements();
        Assert.assertEquals(300.f, fm.Length(), 0.1);
    }

    @Test
    public void nflEndZonesAre30FeetLong() {
        FieldMeasurements fm = new NflFieldMeasurements();
        Assert.assertEquals(30.f, fm.EndZoneLength(), 0.1);
    }

    @Test
    public void nflBorderSizeIs6Feet() {
        FieldMeasurements fm = new NflFieldMeasurements();
        Assert.assertEquals(6.f, fm.BorderSize(), 0.1);
    }

    @Test
    public void nflFieldFullWidthIs172FeetWide() {
        FieldMeasurements fm = new NflFieldMeasurements();
        Assert.assertEquals(172.f, fm.FullFieldWidth(), 0.1);
    }

    @Test
    public void nflFieldFullLengthIs372FeetLong() {
        FieldMeasurements fm = new NflFieldMeasurements();
        Assert.assertEquals(372.f, fm.FullFieldLength(), 0.1);
    }

    @Test
    public void fiftyYardLineReturnsOneEightySix() {
        FieldMeasurements fm = new NflFieldMeasurements();

        int yardLine = 50;
        Assert.assertEquals(186, fm.getFullFieldFootLine(yardLine));
    }
}