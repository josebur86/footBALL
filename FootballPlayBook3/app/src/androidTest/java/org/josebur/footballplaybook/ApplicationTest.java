package org.josebur.footballplaybook;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class ApplicationTest {

    @Test
    public void activityIsNotNull() {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        assertNotNull(activity);
    }
}