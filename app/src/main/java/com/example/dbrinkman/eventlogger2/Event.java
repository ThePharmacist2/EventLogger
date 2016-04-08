package com.example.dbrinkman.eventlogger2;

/**
 * Created by student on 06/04/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Contact is a class that includes a name, phone number, and e-mail address for a contact stored
 * in a phone.*/

public class Event implements Parcelable{

    private String EventName = "";
    private String month = "";
    private String day = "";
    private String TimeFirst = "";
    private String TimeSecond = "";

    /**
     * Contact is a constructor that affects the name, phone, and e-mail information for the
     * contact
     * @param EventNameInput is the name passed by the user
     * @param monthInput is the phone number passed by the user
     * @param dayInput is the e-mail address passed by the user
     */
    public Event(String EventNameInput, String monthInput, String dayInput, String TimeFirstInput, String TimeSecondInput){
        this.EventName = EventNameInput;
        this.month = monthInput;
        this.day = dayInput;
        this.TimeFirst = TimeFirstInput;
        this.TimeSecond = TimeSecondInput;
    }

    /**
     * This is the constructor to demarshal (unpack) information that is stored in a Parcel
     * containing all the information for the contact
     * @param in is the parcel that contains all the information for the contact from the
     *           marshaling process
     */
    public Event(Parcel in){

        readFromParcel(in);

    }

    /**
     * getName is the getter method for the name variable
     * @return the name of the contact
     */
    public String getEventName(){
        return EventName;
    }

    /**
     * getPhone is the getter method for the phone variable
     * @return the phone number of the contact
     */
    public String getMonth(){
        return month;
    }

    /**
     * getEmail is the getter method for the email variable
     * @return the e-mail address of the contact
     */
    public String getDay(){
        return day;
    }

    /**
     * describeContents is a method that must be created when implementing the Parcelable
     * interface, but does not need to be altered
     * @return an integer as a code for the contents of the parcel
     */
    public String getTimeFirst(){
        return TimeFirst;
    }

    public String getTimeSecond(){
        return TimeSecond;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * writeToParcel adds the information from the Contact object to a Parcel so that the
     * information can be transferred between Intents.
     * @param dest is the Parcel to be written to
     * @param flags describes how the informations should be written to the Parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(EventName);
        dest.writeString(month);
        dest.writeString(day);
        dest.writeString(TimeFirst);
        dest.writeString(TimeSecond);
    }

    /**
     * readFromParcel allows information about Contacts to be taken from a Parcel received from
     * another part of the application and set into the variables of the Contact object
     * @param in is the Parcel that contains information about the Contact
     */
    private void readFromParcel(Parcel in){
        EventName = in.readString();
        month = in.readString();
        day = in.readString();
        TimeFirst = in.readString();
        TimeSecond = in.readString();
    }

    /**
     * CREATOR helps to create new Contacts from Parcels and create newArrays of Contacts from
     * Parcelable objects
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in){
            return new Event(in);
        }

        public Event[] newArray(int size){
            return new Event[size];
        }

    };
}