package com.example.expandablenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    lateinit var showNotificationBTN : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        showNotificationBTN = findViewById(R.id.notificationBTN)
        
        showNotificationBTN.setOnClickListener{
            showExpandNotification()
        }
      
    }

    private fun showExpandNotification(){
        var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img)

        var builder = NotificationCompat.Builder(this, "Channel_id").apply {
            setSmallIcon(R.drawable.notification_icon)
            setContentTitle("Expandable Notification")
            setContentText("this is an context")
            setLargeIcon(bitmap)
            setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        // step 2

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("Channel_id", "Test Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel((channel))
        }

        with(NotificationManagerCompat.from(this)){
          notify(1, builder.build())
        }



    }
}