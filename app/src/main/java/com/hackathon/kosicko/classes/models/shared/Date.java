
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Date {

    @SerializedName("epoch")
    @Expose
    public String epoch;
    @SerializedName("pretty")
    @Expose
    public String pretty;
    @SerializedName("day")
    @Expose
    public Integer day;
    @SerializedName("month")
    @Expose
    public Integer month;
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("yday")
    @Expose
    public Integer yday;
    @SerializedName("hour")
    @Expose
    public Integer hour;
    @SerializedName("min")
    @Expose
    public String min;
    @SerializedName("sec")
    @Expose
    public Integer sec;
    @SerializedName("isdst")
    @Expose
    public String isdst;
    @SerializedName("monthname")
    @Expose
    public String monthname;
    @SerializedName("monthname_short")
    @Expose
    public String monthnameShort;
    @SerializedName("weekday_short")
    @Expose
    public String weekdayShort;
    @SerializedName("weekday")
    @Expose
    public String weekday;
    @SerializedName("ampm")
    @Expose
    public String ampm;
    @SerializedName("tz_short")
    @Expose
    public String tzShort;
    @SerializedName("tz_long")
    @Expose
    public String tzLong;

}
