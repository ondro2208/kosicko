
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Features {

    @SerializedName("conditions")
    @Expose
    public Integer conditions;
    @SerializedName("forecast")
    @Expose
    public Integer forecast;

}
