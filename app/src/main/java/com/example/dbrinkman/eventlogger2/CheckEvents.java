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


    public void searchContact(View vw){
//determines what string to search for
        String nameToSearch = edtxtSearchEvent.getText().toString();

        Parcelable[] interim = getIntent().getExtras().getParcelableArray("CONTACTS_ARRAY");

        Event[] events = new Event[interim.length];

       //itterates through the array
        for(int i = 0; i < events.length; i++){
            events[i] = (Event) interim[i];
        }

        String eventInfo = "";

        if(nameToSearch.isEmpty()){
            txtvwEventInfo.setText("You must enter a name to search for a contact!");
        } else {


            for(int j = 0; j < events.length; j++){

              //determines what the text for the even info is and makes sure that the event info is being pulled from the correct event
                if(events[j] != null && nameToSearch.equals(events[j].getEventName())){
                    eventInfo += String.format("Event: %s \nDate: " +
                                    "%s %s\n Time: %s till %s \n\n", events[j].getEventName(), events[j].getMonth(),
                            events[j].getDay(),events[j].getTimeFirst(),events[j].getTimeSecond());
                }

            }

            //displays info for event
            txtvwEventInfo.setText(eventInfo);

        }

    }
}
