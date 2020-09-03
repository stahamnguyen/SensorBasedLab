package com.example.sensorbased

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FinnishPresidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
  val startingYearTextView: TextView = itemView.findViewById(R.id.starting_year_text_view)
  val endingYearTextView: TextView = itemView.findViewById(R.id.ending_year_text_view)
}