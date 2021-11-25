package com.ivanguk10.coffeehouse

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ivanguk10.coffeehouse.ui.MainActivity
import kotlinx.coroutines.coroutineScope


class MyWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val repository: com.ivanguk10.coffeehouse.data.repository.Repository
): CoroutineWorker(context, workerParameters){
    override suspend fun doWork(): Result = coroutineScope {

        val companies = repository.local.getAllNews()

        Log.i("Hello", companies.toString())

        createNotification()
        Result.success()
    }

    private fun createNotification() {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val builder = NotificationCompat.Builder(applicationContext, "1511")
            .setSmallIcon(R.drawable.ic_coffee_24)
            .setContentTitle("Total expense")
            .setContentText("Check the most expensive categories for this month")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        createNotificationChannel()

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(1511, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "EGR"
            val descriptionText = "EGR_desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1511", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}