package org.josebur.footballplaybook;

import android.app.Activity;
import android.graphics.PointF;
import android.os.*;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    private PlayView _playView;
    private PlayerDragListener _dragListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _playView = (PlayView)findViewById(R.id.play_view);

        _dragListener = new PlayerDragListener();
        _playView.setOnDragListener(_dragListener);

        Formation formation = new Formation();
        formation.addPlayer(new Player("LT", 62, 30));
        formation.addPlayer(new Player("LG", 71, 30));
        formation.addPlayer(new Player("C", 80, 30));
        formation.addPlayer(new Player("RG", 89, 30));
        formation.addPlayer(new Player("RT", 98, 30));

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

    private class PlayerDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (v != _playView) return false;
                    // TODO: somehow block other drags from executing.
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    // TODO: Update the active player's location.
                    Log.d("DragEvent.Location", "X: " + event.getX());
                    Log.d("DragEvent.Location", "Y: " + event.getX());

                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DROP:
                    // TODO: Update the active player's location one last time.
                    // TODO: Make sure that the player is no longer active.
                    Log.d("DragEvent.Drop", "Dropped");

                    v.invalidate();
                    return true; //DragEvent.getResult() value

                case DragEvent.ACTION_DRAG_ENDED:

                    if (event.getResult()) {
                        Log.d("DragEvent.Ended", "Drop handled.");
                    } else {
                        Log.d("DragEvent.Ended", "Drop was not handled correctly.");
                    }

                    return true;

                default:
                    Log.e("PlayerDragListener", "Unknown action type.");
                    break;
            }

            return false;
        }
    }
}
