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

    public void setGCMReceiver(int uid,String title,String message)
    {
        user = new User();
        user.setUserId(uid);
        user.setAddress(message);
        user.setImage(title);
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
            case RetrofitHandler.RESULT_RETRIEVE_ALL_JOBS:
                rh.retrieveAllJobs();
                break;
            case RetrofitHandler.RESULT_SEND_NOTIFICATION:
                rh.sendNotification(user.getUserId(),user.getImage(),user.getAddress());
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
