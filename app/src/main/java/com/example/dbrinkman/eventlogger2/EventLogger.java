package com.example.dbrinkman.eventlogger2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EventLogger extends AppCompatActivity {

    private static Event[] eventsArray = new Event[10];
    private static int numEventsAdded = 0;
    private Spinner spnrFirstTime;
    private Spinner spnrDay;
    private Spinner spnrMonth;
    private EditText edtxtEventName;
    private Spinner spnrSecondTime;
    private TextView txtvwError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_logger);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtxtEventName = (EditText) findViewById(R.id.edtxtEventName);
        txtvwError = (TextView) findViewById(R.id.txtvwError);

        spnrFirstTime = (Spinner) findViewById(R.id.spnrFirstTime);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.secondTime_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrFirstTime.setAdapter(adapter);

        spnrSecondTime = (Spinner) findViewById(R.id.spnrSecondTime);
        ArrayAdapter<CharSequence> adapterSecond = ArrayAdapter.createFromResource(this,
        R.array.secondTime_array, android.R.layout.simple_spinner_item);
        adapterSecond.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrSecondTime.setAdapter(adapterSecond);

        spnrDay = (Spinner) findViewById(R.id.spnrDay);
        ArrayAdapter<CharSequence> adapterDay = ArrayAdapter.createFromResource(this,
        R.array.days_array, android.R.layout.simple_spinner_item);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrDay.setAdapter(adapterDay);

        spnrMonth = (Spinner) findViewById(R.id.spnrMonth);
        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(this,
        R.array.months_array, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrMonth.setAdapter(adapterMonth);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_logger, menu);
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

    public void addEvent(View vw) {

        Event eventToAdd;

        if (edtxtEventName.getText().equals("")) {

            txtvwError.setText("You must enter a name to add an event!");

        } else {

            if (numEventsAdded < eventsArray.length) {

                //creates a new Contact object to be added to the array
                eventToAdd = new Event(edtxtEventName.getText().toString(), spnrMonth.getSelectedItem().toString(), spnrDay.getSelectedItem().toString(), spnrFirstTime.getSelectedItem().toString(), spnrSecondTime.getSelectedItem().toString());

                //adds the Contact to the array at the current index
                eventsArray[numEventsAdded] = eventToAdd;

                //increases the current index
                numEventsAdded++;

                //clears the Contact's information
                edtxtEventName.setText("");
                spnrDay.setSelection(0);
                spnrMonth.setSelection(0);
                spnrFirstTime.setSelection(0);
                spnrSecondTime.setSelection(0);

                txtvwError.setText("Event added successfully");

            } else {

                txtvwError.setText("You have maxed out the number of Events you can store in " +
                        "your phone!");

            }

        }
        InputMethodManager inputManager = (InputMethodManager)
                this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(vw.getApplicationWindowToken(), 0);
        //spnrDay.getSelectedItem().toString()

    }

    public void displaySearchActivity(View vw) {

        Intent goToSearch = new Intent(this, CheckEvents.class);

        goToSearch.putExtra("CONTACTS_ARRAY", eventsArray);

        startActivity(goToSearch);

    }
}