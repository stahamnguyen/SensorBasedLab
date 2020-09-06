package com.example.sensorbased.model

data class WikiResponse(val query: Query) {
  data class Query(val searchinfo: SearchInfo) {
    data class SearchInfo(val totalhits: Int)
  }
}