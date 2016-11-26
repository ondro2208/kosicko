
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Forecastday {

    @SerializedName("period")
    @Expose
    public Integer period;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("icon_url")
    @Expose
    public String iconUrl;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("fcttext")
    @Expose
    public String fcttext;
    @SerializedName("fcttext_metric")
    @Expose
    public String fcttextMetric;
    @SerializedName("pop")
    @Expose
    public String pop;

}
