package com.example.dbrinkman.eventlogger2;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dbrinkman.eventlogger2.Event;
import com.example.dbrinkman.eventlogger2.R;

import java.io.Serializable;

public class CheckEvents extends AppCompatActivity {

    EditText edtxtSearchEvent;
    TextView txtvwEventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_events);

        edtxtSearchEvent = (EditText) findViewById(R.id.edtxtSearchEvent);
        txtvwEventInfo = (TextView) findViewById(R.id.txtvwEventInfo);
    }

    /**
     * searchContact converts the passed array from Parcelable[] to Contact[] and loops through
     * the array to search for a specific contact's information and display it for the user
     *
     * @param vw is the button that the searchContact method is connected to
     */
    public void searchContact(View vw){

        String nameToSearch = edtxtSearchEvent.getText().toString();

        Parcelable[] interim = getIntent().getExtras().getParcelableArray("CONTACTS_ARRAY");

        Event[] events = new Event[interim.length];

        //creates Contact objects from Parcelable objects
        for(int i = 0; i < events.length; i++){
            events[i] = (Event) interim[i];
        }

        String contactInfo = "";

        if(nameToSearch.isEmpty()){
            txtvwEventInfo.setText("You must enter a name to search for a contact!");
        } else {

            //iterates through array to search for a specific contact's name
            for(int j = 0; j < events.length; j++){

                //first must check that the element at j is actually a Contact objact
                //and then tests String equivalence
                if(events[j] != null && nameToSearch.equals(events[j].getEventName())){
                    contactInfo += String.format("Event: %s \nDate: " +
                                    "%s %s\n Time: %s till %s \n\n", events[j].getEventName(), events[j].getMonth(),
                            events[j].getDay(),events[j].getTimeFirst(),events[j].getTimeSecond());
                }

            }

            //displays the info of the searched Contact
            txtvwEventInfo.setText(contactInfo);

        }

    }
}
