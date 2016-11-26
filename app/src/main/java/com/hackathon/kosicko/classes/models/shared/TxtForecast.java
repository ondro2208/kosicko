
package com.hackathon.kosicko.classes.models.shared;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class TxtForecast {

    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("forecastday")
    @Expose
    public List<Forecastday> forecastday = new ArrayList<Forecastday>();

}
