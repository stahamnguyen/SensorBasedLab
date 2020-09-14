package com.example.sensorbased

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
  companion object {
    const val REQUEST_IMAGE_CAPTURE = 99
  }

  private lateinit var currentPhotoPath: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fileName = "temp_photo"
    val imgPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    var imageFile: File? = null
    imageFile = File.createTempFile(fileName, ".jpg", imgPath)
    val photoURI: Uri = FileProvider.getUriForFile(this, "com.example.sensorbased.fileprovider", imageFile)

    currentPhotoPath = imageFile.absolutePath

    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (cameraIntent.resolveActivity(packageManager) != null) {
      cameraIntent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
      cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
      startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
      image_view.setImageBitmap(imageBitmap)
    }
  }
}