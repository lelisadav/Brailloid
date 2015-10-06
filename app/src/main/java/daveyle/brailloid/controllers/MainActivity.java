package daveyle.brailloid.controllers;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import daveyle.brailloid.BrailleConverter;
import daveyle.brailloid.R;
import daveyle.brailloid.models.BrailleInput;


public class MainActivity extends Activity implements KeyboardFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void onLettersEntered(List<BrailleInput> inputs) {
        String s="";
        BrailleConverter bc= new BrailleConverter();
        for (BrailleInput i : inputs){
            if (bc.getLetter(i)!=null) {
                s += bc.getLetter(i).getLetter();
            }
        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
