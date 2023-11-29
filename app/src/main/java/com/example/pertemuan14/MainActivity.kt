package com.example.pertemuan14

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.pertemuan14.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private val channelId = "TEST_NOTIF"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val builder = NotificationCompat.Builder(this,
            channelId).setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle("Notifku")
            .setContentText("Henlo Wold")
            .setAutoCancel(true)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            val notifChannel = NotificationChannel(channelId, "Notifku",
            NotificationManager.IMPORTANCE_DEFAULT)
            with(notifManager){
                createNotificationChannel(notifChannel)
                notify(0,builder.build())
            }
        } else {
            notifManager.notify(0,builder.build())
        }
    }
}