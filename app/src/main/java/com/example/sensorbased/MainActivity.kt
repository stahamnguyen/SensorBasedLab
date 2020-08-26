package com.example.sensorbased

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "Lab-2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val availableSubtitles = listOf<String>("Oh, this is the first subtitle", "Oh, another subtitle")

        switch_text_button.setOnClickListener {
            val currentSubtitle = textview_subtitle.text
            val upcomingSubtitle = availableSubtitles[availableSubtitles.indices.random()]
            var snackbarMessage: String

            if (currentSubtitle !== upcomingSubtitle) {
                textview_subtitle.text = upcomingSubtitle
                snackbarMessage = getString(R.string.snackbar_message_subtitle_change)
            } else {
                snackbarMessage = getString(R.string.snackbar_message_subtitle_inact)
            }

            Snackbar.make(
                switch_text_button,
                snackbarMessage,
                Snackbar.LENGTH_LONG
            )
                .setAction(getString(R.string.action_ok)) { }
                .show()
        }

        clear_text_button.setOnClickListener {
            textview_subtitle.text = ""

            val notif = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(getString(R.string.notify_title))
                .setContentText(getString(R.string.notif_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            NotificationManagerCompat.from(this).notify(1, notif)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = getString(R.string.channel_description)
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}