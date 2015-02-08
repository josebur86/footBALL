package org.josebur.libraries;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldMeasurementsTest {

    @Test
    public void fiftyYardLineReturnsOneEightySix() {
        int yardLine = 50;
        Assert.assertEquals(186, FieldMeasurements.getFullFieldFootLine(yardLine));
    }
}