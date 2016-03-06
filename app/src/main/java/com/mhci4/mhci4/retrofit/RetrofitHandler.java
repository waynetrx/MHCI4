package com.mhci4.mhci4.retrofit;


import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitHandler implements Callback<API> {

    public static final String REST_BASE_URL = "http://172.17.193.129:8080";
    public static final int RESULT_SAVE_GCM = 101;
    Retrofit mRetrofit;
    APIServiceInterface mApiService;
    RetrofitCallback mCallback;
    int resultCode = -1;

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
        resultCode = RESULT_SAVE_GCM;
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<API> response, Retrofit retrofit) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    public interface RetrofitCallback
    {
        public void onResponse(int resultCode,boolean result,Object data);
    }

}
