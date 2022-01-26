package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC


class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Loads the activity after clicking the notif?
        val i = Intent(context, HomeActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context!!, "WorkItId")
            .setSmallIcon(R.drawable.ic_app_logo)
            .setContentTitle("WorkIt!")
            .setContentText("Time for you to WorkIt Out!")
            .setSound(soundUri)
            .setPriority(Notification.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVisibility(VISIBILITY_PUBLIC)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())
    }
}