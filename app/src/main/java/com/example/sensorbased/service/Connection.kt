package com.example.sensorbased.service

import android.os.Handler
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Connection(mHand: Handler, private val content: String) : Runnable {
  private val myHandler = mHand

  override fun run() {
    try {
      val myUrl = URL("https://urlecho.appspot.com/echo")
      val myConn = myUrl.openConnection() as HttpURLConnection
      myConn.requestMethod = "POST"
      myConn.doOutput = true
      val ostream = myConn.getOutputStream()
      ostream.bufferedWriter().use {
        it.write(content)
      }
      val istream: InputStream = myConn.getInputStream()
      val allText = istream.bufferedReader().use {
        it.readText()
      }
      val result = StringBuilder()
      result.append(allText)
      val str = result.toString()

      val msg = myHandler.obtainMessage()
      msg.what = 0
      msg.obj = str
      myHandler.sendMessage(msg)
    } catch (e: Exception) {
      //…
    }
  }
}