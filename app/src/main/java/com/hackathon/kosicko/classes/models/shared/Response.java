
package com.hackathon.kosicko.classes.models.shared;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Response {

    @SerializedName("version")
    @Expose
    public String version;
    @SerializedName("termsofService")
    @Expose
    public String termsofService;
    @SerializedName("features")
    @Expose
    public Features features;

}
