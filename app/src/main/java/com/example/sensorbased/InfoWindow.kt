package com.example.sensorbased

import android.widget.TextView
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow

class MyInfoWindow(layoutResId: Int, mapView: MapView) : InfoWindow(layoutResId, mapView) {
  val info_text_view = mView.findViewById<TextView>(R.id.info_text_view)

  override fun onOpen(item: Any?) {
    info_text_view.text = "Metropolia"
  }
  override fun onClose() {
  }

  override fun onDetach() {
    super.onDetach()
  }
}