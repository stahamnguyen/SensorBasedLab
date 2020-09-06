package com.example.sensorbased.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiService {
  @GET("/w/api.php?action=query&format=json&list=search&")
  fun searchNumberOfLinksByPresident(@Query("srsearch") presidentName: String): Call<WikiResponse>

  companion object {
    val instance: WikiService by lazy {
      val retrofit = Retrofit.Builder()
        .baseUrl("https://en.wikipedia.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      retrofit.create<WikiService>(WikiService::class.java)
    }
  }
}