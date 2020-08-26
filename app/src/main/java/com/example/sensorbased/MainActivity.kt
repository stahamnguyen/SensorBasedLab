package com.example.sensorbased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        }
    }
}