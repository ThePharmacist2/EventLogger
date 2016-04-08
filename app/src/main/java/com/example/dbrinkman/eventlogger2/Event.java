package com.example.dbrinkman.eventlogger2;

/**
 * Created by student on 06/04/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;



public class Event implements Parcelable{

    private String EventName = "";
    private String month = "";
    private String day = "";
    private String TimeFirst = "";
    private String TimeSecond = "";

    //describes what the "Event" entails
    public Event(String EventNameInput, String monthInput, String dayInput, String TimeFirstInput, String TimeSecondInput){
        this.EventName = EventNameInput;
        this.month = monthInput;
        this.day = dayInput;
        this.TimeFirst = TimeFirstInput;
        this.TimeSecond = TimeSecondInput;
    }


    public Event(Parcel in){

        readFromParcel(in);

    }

    //sets which variables become which attributes of the event
    public String getEventName(){
        return EventName;
    }


    public String getMonth(){
        return month;
    }


    public String getDay(){
        return day;
    }


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


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(EventName);
        dest.writeString(month);
        dest.writeString(day);
        dest.writeString(TimeFirst);
        dest.writeString(TimeSecond);
    }


    private void readFromParcel(Parcel in){
        EventName = in.readString();
        month = in.readString();
        day = in.readString();
        TimeFirst = in.readString();
        TimeSecond = in.readString();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in){
            return new Event(in);
        }

        public Event[] newArray(int size){
            return new Event[size];
        }

    };
}