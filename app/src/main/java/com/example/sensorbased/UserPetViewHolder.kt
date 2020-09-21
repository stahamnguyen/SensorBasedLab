package com.example.sensorbased

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserPetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var user_name_text_view: TextView
    lateinit var pet_name_text_view: TextView
}