package com.example.sensorbased.repository

import com.example.sensorbased.model.WikiResponse
import com.example.sensorbased.model.WikiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WikiServiceRepo(private val wikiService: WikiService) {
  fun getNumberOfLinks(presidentName: String, callback: (WikiResponse.Query.SearchInfo?) -> Unit) {
    val wikiCall = wikiService.searchNumberOfLinksByPresident(presidentName)
    wikiCall.enqueue(object : Callback<WikiResponse> {
      override fun onFailure(call: Call<WikiResponse>, t: Throwable) {
        callback(null)
      }

      override fun onResponse(call: Call<WikiResponse>, response: Response<WikiResponse>) {
        val body = response.body()
        callback(body?.query?.searchinfo)
      }
    })
  }
}