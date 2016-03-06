package com.mhci4.mhci4.retrofit;

import android.os.AsyncTask;
import android.util.Log;

public class APIAsyncTask extends AsyncTask<Void,Void,Void> {

    private int requestCode;
    private Grocery grocery;
    private User user;

    public APIAsyncTask(int requestCode)
    {
        this.requestCode = requestCode;
    }

    public void setGcmToken(int id,String token)
    {
        user = new User();
        user.setUserId(id);
        user.setGcmToken(token);
    }



    @Override
    protected Void doInBackground(Void... params) {
        switch(requestCode)
        {
            case RetrofitHandler.RESULT_SAVE_GCM :
                RetrofitHandler rh = new RetrofitHandler(null);
                rh.saveGCMToken(user.getUserId(),user.getGcmToken());
                break;

            default:
                break;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }



}
