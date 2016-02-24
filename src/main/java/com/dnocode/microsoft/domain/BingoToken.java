package com.dnocode.microsoft.domain;


import com.google.gson.annotations.SerializedName;

import java.time.Instant;



/**
 * Created by dino on 24/02/16.
 */
public class BingoToken  {

    public static BingoToken EXPIRED=null;
    @SerializedName(value = "access_token")
    public String accessToken;
    @SerializedName(value = "token_type")
    public String tokenType;
    @SerializedName(value = "expire_in")
    public Long expireIn;
    Instant created=Instant.now();
    @SerializedName(value = "scope")
    public String scope;


    public  boolean isExpired(){ return created.plusSeconds(expireIn).isAfter(Instant.now());}

}
