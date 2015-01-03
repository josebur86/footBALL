package org.josebur.footballplaybook;

import android.app.Activity;
import android.content.ClipData;
import android.os.*;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements FieldView.FieldViewListener {

    private Field _field;
    private IPlayer _selectedPlayer = null;

    private FieldView _fieldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _fieldView = (FieldView)findViewById(R.id.field_view);
        _fieldView.setOnDragListener(new PlayerDragListener());
        _fieldView.setFieldViewListener(this);

        Formation formation = new Formation();
        formation.addPlayer(new Player("LT", 62, 30));
        formation.addPlayer(new Player("LG", 71, 30));
        formation.addPlayer(new Player("C", 80, 30));
        formation.addPlayer(new Player("RG", 89, 30));
        formation.addPlayer(new Player("RT", 98, 30));

        _field = new Field();
        _field.setFormation(formation);

        _fieldView.setField(_field);
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
    public void onPlayerLongPressed(IPlayer p) {
        _field.formation().unselectAllPlayers();
        _selectedPlayer = new SelectedPlayer(p);

        FieldView.PlayerDragShadowBuilder shadowBuilder = new FieldView.PlayerDragShadowBuilder();
        ClipData dragData = ClipData.newPlainText("Player", p.label());

        _fieldView.startDrag(dragData, shadowBuilder, null, 0);
    }

    private class PlayerDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (v != _fieldView) return false;

                    String label = event.getClipDescription().getLabel().toString();
                    if (!label.equals("Player")) return false;

                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    if (_selectedPlayer == null) return true;

                    _selectedPlayer.moveTo(_fieldView.fieldTransform().
                            getFeetFromPoint(event.getX(), event.getY()));

                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DROP:
                    _field.formation().unselectAllPlayers();
                    _selectedPlayer = null;

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
