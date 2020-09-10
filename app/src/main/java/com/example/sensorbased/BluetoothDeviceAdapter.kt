package com.example.sensorbased

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BluetoothDeviceAdapter(private val context: Context, private val items: ArrayList<BluetoothDevice>) : BaseAdapter() {
  override fun getCount(): Int {
    return items.size
  }

  override fun getItem(position: Int): BluetoothDevice {
    return items[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    var actualConvertView: View =
      convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_listview, parent, false)

    val currentBluetoothDevice = getItem(position)
    val device_name_text_view = actualConvertView.findViewById<TextView>(R.id.device_name_text_view);
    val mac_address_text_view = actualConvertView.findViewById<TextView>(R.id.mac_address_text_view);
    val rssi_text_view = actualConvertView.findViewById<TextView>(R.id.rssi_text_view);

    device_name_text_view.text = currentBluetoothDevice.name
    mac_address_text_view.text = currentBluetoothDevice.macAddress
    rssi_text_view.text = currentBluetoothDevice.rssi

    if (!currentBluetoothDevice.isConnectible) {
      actualConvertView.isEnabled = false
      device_name_text_view.setTextColor(Color.GRAY)
      mac_address_text_view.setTextColor(Color.GRAY)
      rssi_text_view.setTextColor(Color.GRAY)
    }

    return actualConvertView
  }

  override fun isEnabled(position: Int): Boolean {
    return items[position].isConnectible
  }

  fun addData(data: BluetoothDevice) {
    items.add(data)
  }
}