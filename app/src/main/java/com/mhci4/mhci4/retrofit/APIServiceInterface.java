package com.mhci4.mhci4.retrofit;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface APIServiceInterface
{
    @FormUrlEncoded
    @POST("setGCMToken")
    Call<API> setGCMToken(@Field("user_id") int uid, @Field("gcm_token") String token);

    @FormUrlEncoded
    @POST("setRunner")
    Call<API> setJobRunner(@Field("job_id") int jid, @Field("runner_id") int rid);

    @GET("user/{user_id}")
    Call<API> retrieveUserById(@Path("user_id") int uid);

    @GET("job/{job_id}")
    Call<API> retrieveJobById(@Path("job_id") int jid);

    @GET("retrieveAllJobs")
    Call<API> retrieveAllJobs();

    @FormUrlEncoded
    @POST("notify")
    Call<API> sendNotification(@Field("user_id") int uid, @Field("title") String title ,@Field("message") String message);

}
