
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Forecast {

    @SerializedName("txt_forecast")
    @Expose
    public TxtForecast txtForecast;
    @SerializedName("simpleforecast")
    @Expose
    public Simpleforecast simpleforecast;

}
