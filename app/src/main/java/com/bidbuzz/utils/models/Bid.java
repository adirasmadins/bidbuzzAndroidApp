package com.bidbuzz.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Bid implements Parcelable {
    private String id;
    private String title;
    private Float sale_rate;
    private String img_url;
    private String url;
    private Float bid_rate;



    // Constructor
    public Bid(String id, String title, String grade){
        this.id = id;
        this.title = title;
        this.grade = grade;
    }

    public Bid(JSONObject o){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    /*
    // Getter and setter methods
       .........
               .........

    // Parcelling part*/
    public Bid(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.id = data[0];
        this.name = data[1];
        this.grade = data[2];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.id,
                this.name,
                this.grade});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Bid createFromParcel(Parcel in) {
            return new Bid(in);
        }

        public Bid[] newArray(int size) {
            return new Bid[size];
        }
    };
}