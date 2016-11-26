
package com.hackathon.kosicko.classes.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hackathon.kosicko.classes.models.shared.CurrentObservation;
import com.hackathon.kosicko.classes.models.shared.Response;

@Generated("org.jsonschema2pojo")
public class Conditions {

    @SerializedName("response")
    @Expose
    public Response response;
    @SerializedName("current_observation")
    @Expose
    public CurrentObservation currentObservation;

}
