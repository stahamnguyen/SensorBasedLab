package com.example.sensorbased.service

import android.os.Handler
import java.io.InputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class Connection(mHand: Handler, private val content: String) : Runnable {
  private val myHandler = mHand

  override fun run() {
    try {
      var reqParam = URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")
      reqParam += "&" + URLEncoder.encode("Content-Type", "UTF-8") + "=" + URLEncoder.encode("text/html", "UTF-8")
      reqParam += "&" + URLEncoder.encode("body", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8")

      val myUrl = URL("https://urlecho.appspot.com/echo?$reqParam")

      with(myUrl.openConnection() as HttpURLConnection) {
        requestMethod = "POST"
        doOutput = true

        val allText = inputStream.bufferedReader().use {
          it.readText()
        }
        val result = StringBuilder()
        result.append(allText)
        val str = result.toString()

        val msg = myHandler.obtainMessage()
        msg.what = 0
        msg.obj = str
        myHandler.sendMessage(msg)
      }
    } catch (e: Exception) {
      println(e)
    }
  }
}