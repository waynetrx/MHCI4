package com.mhci4.mhci4.retrofit;


import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitHandler implements Callback<API> {

    //public static final String REST_BASE_URL = "http://192.168.0.108:8080";
    public static final String REST_BASE_URL = "http://electricvibes.net";
    public static final int RESULT_SAVE_GCM = 101;
    public static final int RESULT_RETRIEVE_USER_PROFILE = 102;
    public static final int RESULT_SET_JOB_RUNNER = 103;
    public static final int RESULT_RETRIEVE_JOB = 104;
    public static final int RESULT_RETRIEVE_ALL_JOBS = 105;
    public static final int RESULT_SEND_NOTIFICATION = 106;

    Retrofit mRetrofit;
    APIServiceInterface mApiService;
    RetrofitCallback mCallback;
    int requestCode = -1;

    public RetrofitHandler(RetrofitCallback callback)
    {
        mCallback = callback;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(REST_BASE_URL + "/fyp/api/mhci4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(APIServiceInterface.class);
    }

    public void saveGCMToken(int uid, String token)
    {
        Call<API> call = mApiService.setGCMToken(uid,token);
        requestCode = RESULT_SAVE_GCM;
        call.enqueue(this);
    }

    public void retrieveUserProfile(int uid)
    {
        Call<API> call = mApiService.retrieveUserById(uid);
        requestCode = RESULT_RETRIEVE_USER_PROFILE;
        call.enqueue(this);
    }

    public void retrieveJobByID(int jid)
    {
        Call<API> call = mApiService.retrieveJobById(jid);
        requestCode = RESULT_RETRIEVE_JOB;
        call.enqueue(this);
    }


    public void setJobRunner(int jid,int rid)
    {
        Call<API> call = mApiService.setJobRunner(jid,rid);
        requestCode = RESULT_SET_JOB_RUNNER;
        call.enqueue(this);
    }

    public void retrieveAllJobs()
    {
        Call<API> call = mApiService.retrieveAllJobs();
        requestCode = RESULT_RETRIEVE_ALL_JOBS;
        call.enqueue(this);
    }

    public void sendNotification(int uid,String title,String message)
    {
        Call<API> call = mApiService.sendNotification(uid,title,message);
        requestCode = RESULT_SEND_NOTIFICATION;
        call.enqueue(this);
    }


    @Override
    public void onResponse(Response<API> response, Retrofit retrofit) {

        switch(requestCode)
        {
            case RESULT_RETRIEVE_USER_PROFILE:
                mCallback.onResponse(requestCode,true,response.body().getUser());
                break;
            case RESULT_RETRIEVE_JOB:
                mCallback.onResponse(requestCode,true,response.body().getJobData());
                break;
            case RESULT_RETRIEVE_ALL_JOBS:
                mCallback.onResponse(requestCode,true,response.body().getJobsData());
                break;
            case RESULT_SEND_NOTIFICATION:
                break;
            default:
                break;
        }

    }

    @Override
    public void onFailure(Throwable t) {

    }

    public interface RetrofitCallback
    {
        public void onResponse(int resultCode,boolean result,Object data);
    }

}
