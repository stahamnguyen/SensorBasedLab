package com.example.sensorbased

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  companion object {
    const val SCAN_PERIOD: Long = 3000
    const val REQUEST_LOCATION: Int = 1
  }

  private var bluetoothAdapter: BluetoothAdapter? = null
  private var scanResults: HashMap<String, ScanResult>? = null
  private var adapter: BluetoothDeviceAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    bluetoothAdapter = bluetoothManager.adapter

    adapter = BluetoothDeviceAdapter(this, arrayListOf())
    device_list.adapter = adapter

    button.setOnClickListener {
      if (ActivityCompat.checkSelfPermission(this,
          Manifest.permission.ACCESS_FINE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED) {
        requestLocationPermissions()
      } else {
        scanResults = HashMap();
        val scanCallback = BluetoothLEScanCallback()
        val bluetoothScanner = bluetoothAdapter!!.bluetoothLeScanner

        val settings = ScanSettings.Builder()
          .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
          .build()
        val filter: List<ScanFilter>? = null

        val mHandler = Handler()
        mHandler!!.postDelayed({ bluetoothScanner.stopScan(scanCallback) }, SCAN_PERIOD)

        bluetoothScanner!!.startScan(filter, settings, scanCallback)
      }
    }
  }

  private fun requestLocationPermissions() {
    ActivityCompat.requestPermissions(this,
      arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
      REQUEST_LOCATION)
  }

  private inner class BluetoothLEScanCallback : ScanCallback() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onScanResult(callbackType: Int, result: ScanResult?) {
      println(result);
      processScanResult(result)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBatchScanResults(results: MutableList<ScanResult>?) {
      println(results);
      if (results != null) {
        for (result in results) {
          processScanResult(result)
        }
      }
    }

    override fun onScanFailed(errorCode: Int) {
      Log.d("DBG", "BLE Scan Failed with code $errorCode")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun processScanResult(result: ScanResult?) {
      if (result != null) {
        val device = result.device
        adapter?.addData(BluetoothDevice(device.name ?: "Unknown", device.address, result.rssi.toString(), result.isConnectable))
        adapter?.notifyDataSetChanged()
      }
    }
  }
}