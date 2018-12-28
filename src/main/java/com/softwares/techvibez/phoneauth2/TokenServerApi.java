package com.softwares.techvibez.phoneauth2;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenServerApi {
    @POST("/verify/token")
    @FormUrlEncoded
    retrofit2.Call<TokenSeverResponse> getToken(@Field("phone_number") String phoneNumber);
}
