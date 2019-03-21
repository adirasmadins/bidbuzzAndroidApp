package com.bidbuzz.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Item implements Parcelable {
    private Integer id = null;
    private String title = null;
    private Double mrp = null;
    private Double discounted_rate = null;
    private Double sale_rate = null;
    private String description = null;
    private JSONObject primary_images = null;
    private String amazon_link = null;
    private String flipkart_link = null;

    // Constructor
    public Item(Integer id, String title, Double mrp, Double discounted_rate, Double sale_rate, String description, JSONObject primary_images, String amazon_link, String flipkart_link){
        this.id = id;
        this.title = title;
        this.mrp = mrp;
        this.discounted_rate = discounted_rate;
        this.sale_rate = sale_rate;
        this.description = description;
        this.primary_images = primary_images;
        this.amazon_link = amazon_link;
        this.flipkart_link = flipkart_link;
    }

    public Item(JSONObject o){
        try{
            this.id = o.has("id")?o.getInt("id"):null;
            this.title = o.has("title")?o.getString("title"):null;
            this.mrp = o.has("mrp")?o.getDouble("mrp"):null;
            this.discounted_rate = o.has("discounted_rate")?o.getDouble("discounted_rate"):null;
            this.sale_rate = o.has("sale_rate")?o.getDouble("sale_rate"):null;
            this.description = o.has("description")?o.getString("description"):null;
            this.primary_images = o.has("primary_images")?o.getJSONObject("primary_images"):null;
            this.amazon_link = o.has("amazon_link")?o.getString("amazon_link"):null;
            this.flipkart_link = o.has("flipkart_link")?o.getString("flipkart_link"):null;
        }catch(JSONException e){

        }
    }
    /*
    // Getter and setter methods
       .........
               .........

    // Parcelling part*/
    public Item(Parcel in){
        String[] data = new String[9];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.id = data[0];
        this.title = title;
        this.mrp = mrp;
        this.discounted_rate = discounted_rate;
        this.sale_rate = sale_rate;
        this.description = description;
        this.primary_images = primary_images;
        this.amazon_link = amazon_link;
        this.flipkart_link = flipkart_link;
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