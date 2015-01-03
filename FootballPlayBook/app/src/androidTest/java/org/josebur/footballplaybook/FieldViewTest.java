package org.josebur.footballplaybook;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

public class FieldViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Instrumentation _instr;
    private MainActivity _activity;
    private FieldView _view;

    public FieldViewTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        _instr = getInstrumentation();
        _activity = getActivity();
        _view = (FieldView)_activity.findViewById(R.id.field_view);
    }

    public void testFieldViewDoesNotCrashAfterPauseAndResume() {

        // The activity should be started already, pause it.
        _instr.callActivityOnPause(_activity);

//        assertEquals(Thread.State.TERMINATED, _view.getThreadState());

        // Restart the activity. It should not crash.
        _instr.callActivityOnResume(_activity);
    }
}
