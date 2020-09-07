package com.example.sensorbased

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
  private lateinit var sm: SensorManager
  private var sAmbientTemp: Sensor? = null
  private var sensorAvailable: Boolean? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    if (sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
      sAmbientTemp = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
      sensorAvailable = true
    } else {
//      temperature_text_view.text = "There's no ambient temperature sensor."
      sensorAvailable = false
    }
  }

  override fun onSensorChanged(p0: SensorEvent?) {
    if (p0?.sensor == sAmbientTemp) {
      temperature_text_view.text = "${(p0?.values?.get(0) ?: 0).toString()} °C"
    }
  }

  override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

  }

  override fun onResume() {
    super.onResume()
    if (sensorAvailable!!) {
      sAmbientTemp.also {
        sm.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
      }
    }
  }

  override fun onPause() {
    super.onPause()
    if (sensorAvailable!!) {
      sm.unregisterListener(this)
    }
  }
}