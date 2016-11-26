
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Forecastday_ {

    @SerializedName("date")
    @Expose
    public Date date;
    @SerializedName("period")
    @Expose
    public Integer period;
    @SerializedName("high")
    @Expose
    public High high;
    @SerializedName("low")
    @Expose
    public Low low;
    @SerializedName("conditions")
    @Expose
    public String conditions;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("icon_url")
    @Expose
    public String iconUrl;
    @SerializedName("skyicon")
    @Expose
    public String skyicon;
    @SerializedName("pop")
    @Expose
    public Integer pop;
    @SerializedName("qpf_allday")
    @Expose
    public QpfAllday qpfAllday;
    @SerializedName("qpf_day")
    @Expose
    public QpfDay qpfDay;
    @SerializedName("qpf_night")
    @Expose
    public QpfNight qpfNight;
    @SerializedName("snow_allday")
    @Expose
    public SnowAllday snowAllday;
    @SerializedName("snow_day")
    @Expose
    public SnowDay snowDay;
    @SerializedName("snow_night")
    @Expose
    public SnowNight snowNight;
    @SerializedName("maxwind")
    @Expose
    public Maxwind maxwind;
    @SerializedName("avewind")
    @Expose
    public Avewind avewind;
    @SerializedName("avehumidity")
    @Expose
    public Integer avehumidity;
    @SerializedName("maxhumidity")
    @Expose
    public Integer maxhumidity;
    @SerializedName("minhumidity")
    @Expose
    public Integer minhumidity;

}
