package org.josebur.footballplaybook;

import android.app.Activity;
import android.graphics.PointF;
import android.os.*;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private PlayView _playView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _playView = (PlayView)findViewById(R.id.play_view);

        Formation formation = new Formation();
        formation.addPlayer(new Player(62, 30));
        formation.addPlayer(new Player(71, 30));
        formation.addPlayer(new Player(80, 30));
        formation.addPlayer(new Player(89, 30));
        formation.addPlayer(new Player(98, 30));

        _playView.setFormation(formation);
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
}
