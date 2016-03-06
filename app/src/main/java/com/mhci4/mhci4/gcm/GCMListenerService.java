package com.mhci4.mhci4.gcm;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.mhci4.mhci4.R;


public class GCMListenerService extends GcmListenerService
{


    @Override
    public void onMessageReceived(String from, Bundle data) {
        int responseCode = Integer.parseInt(data.getString("responseCode"));
        String message = data.getString("message");
        Log.i("gcmlistenerservice","From: " + from + ", Message: " + message);
        sendNotification(message);
        /*switch(responseCode)
        {
            case 1:
                String message = data.getString("job_id");
                break;
            case 2:

                sendNotification(message);
                break;
        }*/

    }


    public void sendNotification(String message)
    {
        int mNotificationId = 001;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notification_24)
                        .setContentTitle("New Notification")
                        .setContentText(message);
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId,mBuilder.build());

    }
}
