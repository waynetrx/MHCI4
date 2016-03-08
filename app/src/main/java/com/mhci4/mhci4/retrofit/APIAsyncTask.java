package com.mhci4.mhci4.retrofit;

import android.os.AsyncTask;

public class APIAsyncTask extends AsyncTask<Void,Void,Void> {

    private int requestCode;
    private Grocery grocery;
    private User user;
    private RetrofitHandler.RetrofitCallback mCallBack;

    public APIAsyncTask(int requestCode,RetrofitHandler.RetrofitCallback callback)
    {
        this.requestCode = requestCode;
        this.mCallBack = callback;
    }

    public void setGcmToken(int id,String token)
    {
        user = new User();
        user.setUserId(id);
        user.setGcmToken(token);
    }

    public void setUserID(int uid)
    {
        user = new User();
        user.setUserId(uid);
    }

    public void setJobID(int jid)
    {
        grocery = new Grocery();
        grocery.setJid(jid);
    }

    public void setJobRunner(int jid, int rid)
    {
        grocery = new Grocery();
        grocery.setJid(jid);
        grocery.setRid(rid);
    }




    @Override
    protected Void doInBackground(Void... params) {
        RetrofitHandler rh = new RetrofitHandler(mCallBack);
        switch(requestCode)
        {
            case RetrofitHandler.RESULT_SAVE_GCM :
                rh.saveGCMToken(user.getUserId(),user.getGcmToken());
                break;
            case RetrofitHandler.RESULT_RETRIEVE_USER_PROFILE:
                rh.retrieveUserProfile(user.getUserId());
                break;
            case RetrofitHandler.RESULT_RETRIEVE_JOB:
                rh.retrieveJobByID(grocery.getJid());
                break;
            case RetrofitHandler.RESULT_SET_JOB_RUNNER:
                rh.setJobRunner(grocery.getJid(),grocery.getRid());
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
