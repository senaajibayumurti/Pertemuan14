package com.example.pertemuan14

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.pertemuan14.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST_NOTIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val notifId = 0
        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.btnNotif.setOnClickListener {
                val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    PendingIntent.FLAG_IMMUTABLE
                } else {
                    0
                }
                val intent = Intent(this, MainActivity::class.java)
    //            val intent = Intent(this, NotifReceiver::class.java)
    //            .putExtra("MESSAGE", "Baca selengkapnya ...")
                val pendingIntent = PendingIntent.getActivity(
                    this, 0, intent, flag
                )
    //            val pendingIntent = PendingIntent.getBroadcast(
    //                this, 0, intent, flag
    //            )


            val builder = NotificationCompat.Builder(this,
                channelId).setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Notifku")
                .setContentText("Henlo Wold")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
    //            .addAction(0, "Baca Notif", pendingIntent)

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
        binding.btnUpdate.setOnClickListener {
            val notifImage = BitmapFactory.decodeResource(
                resources, R.drawable.gambar_nickher
            )
            val builder = NotificationCompat.Builder(this,
                channelId).setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Notifku")
                .setContentText("Henlo Wold")
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(notifImage)
                )
                .setAutoCancel(true)
            notifManager.notify(notifId, builder.build())
        }
    }
}