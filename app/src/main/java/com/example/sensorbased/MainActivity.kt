package com.example.sensorbased

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.preference.PreferenceManager
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.info_window.view.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow
import org.osmdroid.views.overlay.infowindow.InfoWindow
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
  companion object {
    private const val REQUEST_LOCATION = 1
  }

  private lateinit var fusedLocationClient: FusedLocationProviderClient
  private lateinit var locationRequest: LocationRequest
  private var currentLocation: Location? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val ctx = applicationContext
    Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))
    setContentView(R.layout.activity_main)

    setupLocationClient()
    getCurrentLocation()

    setupMap()

    val marker = Marker(map)
    marker.position = GeoPoint(60.2239, 24.7585)
    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
    marker.infoWindow = MyInfoWindow(R.layout.info_window, map)
    marker.infoWindow.open(MyInfoWindow(R.layout.info_window, map), GeoPoint(60.2239,24.7585), 0, -125)
    map.overlays.add(marker)

    startLocationUpdate()
  }

  private fun setupLocationClient() {
    fusedLocationClient =
      LocationServices.getFusedLocationProviderClient(this)
  }

  private fun getCurrentLocation() {
    if (ActivityCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION) !=
      PackageManager.PERMISSION_GRANTED) {
      requestLocationPermissions()
    } else {
      fusedLocationClient.lastLocation.addOnCompleteListener {
        if (it.result != null) {
          currentLocation = Location("Current Location")
          currentLocation!!.longitude = it.result!!.longitude
          currentLocation!!.latitude = it.result!!.latitude
          map.controller.setCenter(GeoPoint(currentLocation!!.latitude, currentLocation!!.longitude))
        }
      }
    }
  }

  private fun requestLocationPermissions() {
    ActivityCompat.requestPermissions(this,
      arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
      REQUEST_LOCATION)
  }

  private fun setupMap() {
    map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
    map.setMultiTouchControls(true)
    map.controller.setZoom(16.0)
  }

  private fun startLocationUpdate() {
    if (ActivityCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION) !=
      PackageManager.PERMISSION_GRANTED) {
      requestLocationPermissions()
    } else {
      locationRequest = LocationRequest()
      with(locationRequest) {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 10000
        setFastestInterval(5000)
      }

      val locationRequestBuilder = LocationSettingsRequest.Builder()
      locationRequestBuilder.addLocationRequest(locationRequest)
      val locationSettingsRequest = locationRequestBuilder.build()

      val settingsClient = LocationServices.getSettingsClient(this)
      settingsClient.checkLocationSettings(locationSettingsRequest)

      fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
          super.onLocationResult(p0)

          if (p0 != null) {
            if (currentLocation != null) {
              val distance = p0.lastLocation.distanceTo(currentLocation)
              if (distance > 2) {
                textview_small.text = "${distance.toString()}m"
              }
            }
            currentLocation = p0.lastLocation
            map.controller.setCenter(GeoPoint(currentLocation!!.latitude, currentLocation!!.longitude))
          }
        }
      },
        Looper.myLooper())
    }
  }
}