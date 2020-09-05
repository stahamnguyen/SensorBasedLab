package com.example.sensorbased

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class MainActivity() : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val imageUrl = URL("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
    lifecycleScope.launch(Dispatchers.Main) {
      val retrievedBitmap = getImage(imageUrl)
      image_view.setImageBitmap(retrievedBitmap)
    }
  }

  private suspend fun getImage(url: URL): Bitmap? =
    withContext(Dispatchers.IO) {
      with(url.openConnection()) {
        try {
          return@withContext BitmapFactory.decodeStream(getInputStream())
        } catch (e: IOException) {
          println(e)
          return@withContext null
        }
      }
    }
}