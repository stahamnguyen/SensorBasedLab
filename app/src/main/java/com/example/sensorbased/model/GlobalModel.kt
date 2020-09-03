package com.example.sensorbased.model

object GlobalModel {
  val presidents = mutableListOf<President>()

  init {
    presidents.add(President(name = "Kaarlo Juho Ståhlberg", startingYear = 1919, endingYear = 1925, description = "Eka presidentti"))
    presidents.add(President(name = "Lauri Kristian Relander", startingYear = 1925, endingYear = 1931, description = "Toka presidentti"))
    presidents.add(President(name = "Pehr Evind Svinhufvud", startingYear = 1931, endingYear = 1937, description = "Kolmas presidentti"))
    presidents.add(President(name = "Kyösti Kallio", startingYear = 1937, endingYear = 1940, description = "Neljas presidentti"))
    presidents.add(President(name = "Risto Ryti", startingYear = 1940, endingYear = 1944, description = "Viides presidentti"))
    presidents.add(President(name = "Carl Gustaf Emil Mannerheim", startingYear = 1944, endingYear = 1946, description = "Kuudes presidentti"))
    presidents.add(President(name = "Juho Kusti Paasikivi", startingYear = 1946, endingYear = 1956, description = "Äkäinen ukko"))
    presidents.add(President(name = "Urho Kekkonen", startingYear = 1956, endingYear = 1982, description = "Pelimies"))
    presidents.add(President(name = "Mauno Koivisto", startingYear = 1982, endingYear = 1994, description = "Manu"))
    presidents.add(President(name = "Martti Ahtisaari", startingYear = 1994, endingYear = 2000, description = "Mahtisaari"))
    presidents.add(President(name = "Tarja Halonen", startingYear = 2000, endingYear = 2012, description = "Eka naispresidentti"))
  }
}