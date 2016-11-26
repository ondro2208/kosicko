
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Maxwind {

    @SerializedName("mph")
    @Expose
    public Integer mph;
    @SerializedName("kph")
    @Expose
    public Integer kph;
    @SerializedName("dir")
    @Expose
    public String dir;
    @SerializedName("degrees")
    @Expose
    public Integer degrees;

}
