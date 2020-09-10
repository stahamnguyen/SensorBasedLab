package com.example.sensorbased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val adapter = BluetoothDeviceAdapter(this, arrayListOf(BluetoothDevice("test", "test", "test")))
    device_list.adapter = adapter
  }
}