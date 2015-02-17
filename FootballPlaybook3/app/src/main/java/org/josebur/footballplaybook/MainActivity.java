package org.josebur.footballplaybook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.josebur.libraries.Field;
import org.josebur.libraries.FieldMeasurements;
import org.josebur.libraries.NflFieldMeasurements;
import org.josebur.libraries.PlayFieldProperties;
import org.josebur.libraries.Position;


public class MainActivity extends Activity implements PlayFieldProperties {

    PlayView _view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _view = (PlayView)findViewById(R.id.play_view);
        _view.setField(new Field(this));
        _view.setPlayFieldProperties(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public float width() {
        FieldMeasurements measurements = new NflFieldMeasurements();
        return measurements.Width() + 2 * measurements.BorderSize();
    }

    @Override
    public float length() {
        FieldMeasurements measurements = new NflFieldMeasurements();
        return measurements.Length() + 2 * measurements.BorderSize()
                                     + 2 * measurements.EndZoneLength();
    }

    @Override
    public Position ballSpot() {
        return new Position(width() / 2.f, length() / 2.f);
    }
}
