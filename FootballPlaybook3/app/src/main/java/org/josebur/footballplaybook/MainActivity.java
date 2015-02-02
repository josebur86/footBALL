package org.josebur.footballplaybook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.josebur.libraries.Field;
import org.josebur.libraries.FieldMeasurements;
import org.josebur.libraries.PlayFieldProperties;
import org.josebur.libraries.PlayTransform;


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
        return FieldMeasurements.Width + 2 * FieldMeasurements.BorderSize;
    }

    @Override
    public float length() {
        return FieldMeasurements.Length + 2 * FieldMeasurements.BorderSize
                                        + 2 * FieldMeasurements.EndZoneLength;
    }

    @Override
    public float ballSpotFeetX() {
        return width() / 2.f;
    }

    @Override
    public float ballSpotFeetY() {
        return length() / 2.f;
    }
}
