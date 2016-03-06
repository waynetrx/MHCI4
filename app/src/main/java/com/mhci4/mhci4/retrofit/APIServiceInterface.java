package com.mhci4.mhci4.retrofit;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface APIServiceInterface
{
    @FormUrlEncoded
    @POST("setGCMToken")
    Call<API> setGCMToken(@Field("user_id") int uid, @Field("gcm_token") String token);
}
