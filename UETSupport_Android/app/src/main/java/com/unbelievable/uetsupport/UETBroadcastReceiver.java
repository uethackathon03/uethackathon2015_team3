package com.unbelievable.uetsupport;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.unbelievable.uetsupport.common.Constant;

/**
 * Created by DucAnhZ on 22/11/2015.
 */
public class UETBroadcastReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (intent.getAction().equals(Constant.GCM_RECEIVED_ACTION)) {
            // Handle notification
            notificationManager.notify(0,
                    notificationFromServer(context, "Nội dung thông báo !" ));

        }
    }

    private Notification notificationFromServer(Context context, String message) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        android.app.Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle("UETSupport Thông Báo")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true).setContentIntent(pendingIntent)
                .setPriority(Integer.MAX_VALUE)
                .build();

        return notification;
    }
}
