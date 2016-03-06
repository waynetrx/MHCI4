package com.mhci4.mhci4.gcm;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


public class GCMHandler {

    private static final int ACTION_PLAY_SERVICES_DIALOG = 100;
    private Context mContext;
    //    private Activity activity;
    private GoogleCloudMessaging gcm;
    public GCMHandler(Context context)
    {
        this.mContext = context;
//        this.activity = activity;
    }

    public void initialize()
    {
        boolean result = isGooglePlayInstalled();
        if(result)
        {
            Intent intent = new Intent(mContext, GCMRegistrationIntentService.class);
            mContext.startService(intent);
//            activity.startService(intent);
        }
        else
            Log.i("GCMHandler","Google play service is not available.");
    }

    private boolean isGooglePlayInstalled()
    {
        boolean result = false;
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
        if(resultCode == ConnectionResult.SUCCESS)
        {
            result = true;

        }
        else
        {
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(resultCode,(Activity)mContext,ACTION_PLAY_SERVICES_DIALOG).show();
            }
            else
                Toast.makeText(mContext,"Google play service not available!",Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    private class GCMRegistrationTask extends AsyncTask<Void,Void,String>
    {
        private Activity activity;
        private String gcmRegId;
        private static final String GCM_SENDER_ID = "394231745826";

        public GCMRegistrationTask(Activity activity)
        {
            this.activity = activity;
        }

        @Override
        protected String doInBackground(Void... params) {
            if(gcm == null && isGooglePlayInstalled())
            {
                gcm = GoogleCloudMessaging.getInstance(activity);
            }

            InstanceID instanceID = InstanceID.getInstance(activity);
            try {
                String token = instanceID.getToken(GCM_SENDER_ID,GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}