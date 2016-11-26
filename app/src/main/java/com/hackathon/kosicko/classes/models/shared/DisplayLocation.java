
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DisplayLocation {

    @SerializedName("full")
    @Expose
    public String full;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("state_name")
    @Expose
    public String stateName;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("country_iso3166")
    @Expose
    public String countryIso3166;
    @SerializedName("zip")
    @Expose
    public String zip;
    @SerializedName("magic")
    @Expose
    public String magic;
    @SerializedName("wmo")
    @Expose
    public String wmo;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("elevation")
    @Expose
    public String elevation;

}
