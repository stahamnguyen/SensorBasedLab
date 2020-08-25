package com.example.sensorbased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val availableSubtitles = listOf<String>("Oh, this is the first subtitle", "Oh, another subtitle")

        switch_text_button.setOnClickListener {
            textview_subtitle.text = availableSubtitles[availableSubtitles.indices.random()]
        }

        clear_text_button.setOnClickListener {
            textview_subtitle.text = ""
        }
    }
}