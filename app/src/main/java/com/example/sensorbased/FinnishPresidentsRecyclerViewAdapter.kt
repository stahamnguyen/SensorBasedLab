package com.example.sensorbased

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sensorbased.model.President

class FinnishPresidentsRecyclerViewAdapter(private val data: MutableList<President>) : RecyclerView.Adapter<FinnishPresidentViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinnishPresidentViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.finnish_president_view_holder, parent, false)
    return FinnishPresidentViewHolder(view)
  }

  override fun onBindViewHolder(holder: FinnishPresidentViewHolder, position: Int) {
    holder.nameTextView.text = data[position].name
    holder.startingYearTextView.text = data[position].startingYear.toString()
    holder.endingYearTextView.text = data[position].endingYear.toString()
  }

  override fun getItemCount(): Int {
    return data.size
  }
}