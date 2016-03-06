package com.mhci4.mhci4.gcm;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

public class GCMInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        Log.i("ONTOKENREFRESH","Token has been refreshed");
        Intent intent = new Intent(this,GCMRegistrationIntentService.class);
        startService(intent);
    }

}
