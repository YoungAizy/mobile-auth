package com.softwares.techvibez.phoneauth2;

import com.google.gson.annotations.SerializedName;

public class TokenSeverResponse {
    @SerializedName("jwt_token")
    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
