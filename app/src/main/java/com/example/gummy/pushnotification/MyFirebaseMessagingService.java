
package com.example.gummy.pushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    private static final String CHANNEL_ID = "com.example.gummy.pushnotification";
    private int notificationId = 1401;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Map<String, String> data = remoteMessage.getData();
        String title = data.get("title");
        String body = data.get("body");
        String flag = data.get("flag");

        Intent intent = null;
        assert flag != null;
        if (flag.equals("1")){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else {
            intent = new Intent(getApplicationContext(), Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        //Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        createNotificationChannel();

        Log.d(TAG, "onMessageReceived: title = " + title);
        Log.d(TAG, "onMessageReceived: body = " + body);

        // Create Notification
        //

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410,
                intent, PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_active) // small icon
                .setContentTitle(title) // title
                .setContentText(body) // body
                .setAutoCancel(true) // remove notification on tap
                .setPriority(NotificationCompat.PRIORITY_HIGH) // priority
                .setCategory(NotificationCompat.CATEGORY_MESSAGE) // system wide category
                .setContentIntent(pendingIntent); // set tap action

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationBuilder.setSound(uri); // set to default notification sound
        notificationManager.notify(notificationId, notificationBuilder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

// evXvaISaMRE:APA91bHyzEfpkVTiqnmSihMz_5KiB5tUPMzHeJzeZhObCpuqiFGLvl-osYgRPpqxEnW_r8G84Lc5XrmES7otFseFPE13ueGGrvIe_U1fy8AxzN9PhIiAbYb8-hqPJO6jD0GR60fZCPhQ

/*
Authorization   key=AAAAf2WRGCM:APA91bE6P8y7J7qnC-eNLb575A_DaOf-UxXelwiFFTkLDvPIiszs-EEIUPajcdHKZ9H6wABVE8ncaTeB31wOqmyHhr-xv_g2Y3Udpk8by5R5pmIvXqfh8wCGi6hOWYu-PPbwj-qr8t2c
Content-Type    application/json

--> send display message when app is in foreground
{
    "to": "evXvaISaMRE:APA91bHyzEfpkVTiqnmSihMz_5KiB5tUPMzHeJzeZhObCpuqiFGLvl-osYgRPpqxEnW_r8G84Lc5XrmES7otFseFPE13ueGGrvIe_U1fy8AxzN9PhIiAbYb8-hqPJO6jD0GR60fZCPhQ",
    "notification": {
        "body": "test message",
        "title": "Title"
    },
    "priority": "high"
}


--> send data message when app is in background
{
    "data" : {
        "body": "test message",
        "title": "Title"
    },
    "registration_ids": ["{e5_xGuhyEVc:APA91bHfejlGXzXIPnW7pfjpiimhOnj2b-cIe2NOZrQe919nnGMSZsi2FIZ-1Dvotor0puwRXeMiRPwsY1KQPyFTVAzPvPe0oxEV4LmcMa2C4mRlyR3ZsLZ6EVtqb0XIMsuPmIQu1FD6}"]
}


{
    "to": "evXvaISaMRE:APA91bHyzEfpkVTiqnmSihMz_5KiB5tUPMzHeJzeZhObCpuqiFGLvl-osYgRPpqxEnW_r8G84Lc5XrmES7otFseFPE13ueGGrvIe_U1fy8AxzN9PhIiAbYb8-hqPJO6jD0GR60fZCPhQ",
    "notification": {
        "body": "test message",
        "title": "Title"
    },
    "data" : {
        "body": "test message",
        "title": "Title"
    }
}



{
    "multicast_id": 6486797455435266956,
    "success": 1,
    "failure": 0,
    "canonical_ids": 0,
    "results": [
        {
            "message_id": "0:1548406096005496%4a8c60794a8c6079"
        }
    ]
}
 */
