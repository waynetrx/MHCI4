package com.mhci4.mhci4.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.mhci4.mhci4.retrofit.APIAsyncTask;
import com.mhci4.mhci4.retrofit.RetrofitHandler;

import java.io.IOException;

public class GCMRegistrationIntentService extends IntentService {
    private static final String GCM_SENDER_ID = "166683549756";
    public GCMRegistrationIntentService() {
        super(GCMRegistrationIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(GCM_SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.i("GCMRegistrationService", "GCM Registration Token: " + token);
            /*instanceID.deleteInstanceID();
            token = instanceID.getToken(GCM_SENDER_ID,GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.i(TAG, "After GCM Registration Token: " + token);*/
            saveTokenToServer(2,token);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveTokenToServer(int uid, String token)
    {
        APIAsyncTask task = new APIAsyncTask(RetrofitHandler.RESULT_SAVE_GCM);
        task.setGcmToken(uid,token);
        task.execute();
    }
}
