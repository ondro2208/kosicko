
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CurrentObservation {

    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("display_location")
    @Expose
    public DisplayLocation displayLocation;
    @SerializedName("observation_location")
    @Expose
    public ObservationLocation observationLocation;
    @SerializedName("estimated")
    @Expose
    public Estimated estimated;
    @SerializedName("station_id")
    @Expose
    public String stationId;
    @SerializedName("observation_time")
    @Expose
    public String observationTime;
    @SerializedName("observation_time_rfc822")
    @Expose
    public String observationTimeRfc822;
    @SerializedName("observation_epoch")
    @Expose
    public String observationEpoch;
    @SerializedName("local_time_rfc822")
    @Expose
    public String localTimeRfc822;
    @SerializedName("local_epoch")
    @Expose
    public String localEpoch;
    @SerializedName("local_tz_short")
    @Expose
    public String localTzShort;
    @SerializedName("local_tz_long")
    @Expose
    public String localTzLong;
    @SerializedName("local_tz_offset")
    @Expose
    public String localTzOffset;
    @SerializedName("weather")
    @Expose
    public String weather;
    @SerializedName("temperature_string")
    @Expose
    public String temperatureString;
    @SerializedName("temp_f")
    @Expose
    public Double tempF;
    @SerializedName("temp_c")
    @Expose
    public Double tempC;
    @SerializedName("relative_humidity")
    @Expose
    public String relativeHumidity;
    @SerializedName("wind_string")
    @Expose
    public String windString;
    @SerializedName("wind_dir")
    @Expose
    public String windDir;
    @SerializedName("wind_degrees")
    @Expose
    public Integer windDegrees;
    @SerializedName("wind_mph")
    @Expose
    public Double windMph;
    @SerializedName("wind_gust_mph")
    @Expose
    public String windGustMph;
    @SerializedName("wind_kph")
    @Expose
    public Double windKph;
    @SerializedName("wind_gust_kph")
    @Expose
    public String windGustKph;
    @SerializedName("pressure_mb")
    @Expose
    public String pressureMb;
    @SerializedName("pressure_in")
    @Expose
    public String pressureIn;
    @SerializedName("pressure_trend")
    @Expose
    public String pressureTrend;
    @SerializedName("dewpoint_string")
    @Expose
    public String dewpointString;
    @SerializedName("dewpoint_f")
    @Expose
    public Integer dewpointF;
    @SerializedName("dewpoint_c")
    @Expose
    public Integer dewpointC;
    @SerializedName("heat_index_string")
    @Expose
    public String heatIndexString;
    @SerializedName("heat_index_f")
    @Expose
    public String heatIndexF;
    @SerializedName("heat_index_c")
    @Expose
    public String heatIndexC;
    @SerializedName("windchill_string")
    @Expose
    public String windchillString;
    @SerializedName("windchill_f")
    @Expose
    public String windchillF;
    @SerializedName("windchill_c")
    @Expose
    public String windchillC;
    @SerializedName("feelslike_string")
    @Expose
    public String feelslikeString;
    @SerializedName("feelslike_f")
    @Expose
    public String feelslikeF;
    @SerializedName("feelslike_c")
    @Expose
    public String feelslikeC;
    @SerializedName("visibility_mi")
    @Expose
    public String visibilityMi;
    @SerializedName("visibility_km")
    @Expose
    public String visibilityKm;
    @SerializedName("solarradiation")
    @Expose
    public String solarradiation;
    @SerializedName("UV")
    @Expose
    public String uV;
    @SerializedName("precip_1hr_string")
    @Expose
    public String precip1hrString;
    @SerializedName("precip_1hr_in")
    @Expose
    public String precip1hrIn;
    @SerializedName("precip_1hr_metric")
    @Expose
    public String precip1hrMetric;
    @SerializedName("precip_today_string")
    @Expose
    public String precipTodayString;
    @SerializedName("precip_today_in")
    @Expose
    public String precipTodayIn;
    @SerializedName("precip_today_metric")
    @Expose
    public String precipTodayMetric;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("icon_url")
    @Expose
    public String iconUrl;
    @SerializedName("forecast_url")
    @Expose
    public String forecastUrl;
    @SerializedName("history_url")
    @Expose
    public String historyUrl;
    @SerializedName("ob_url")
    @Expose
    public String obUrl;
    @SerializedName("nowcast")
    @Expose
    public String nowcast;

}
