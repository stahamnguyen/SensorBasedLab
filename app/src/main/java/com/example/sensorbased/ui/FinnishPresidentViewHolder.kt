package com.example.sensorbased.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sensorbased.R

class FinnishPresidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
  val startingYearTextView: TextView = itemView.findViewById(R.id.starting_year_text_view)
  val endingYearTextView: TextView = itemView.findViewById(R.id.ending_year_text_view)
}