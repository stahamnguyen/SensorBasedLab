package com.example.sensorbased.model

data class President(val name: String, val startingYear: Int, val endingYear: Int, val description: String) {
  override fun toString(): String {
    return "$name $startingYear $endingYear"
  }
}