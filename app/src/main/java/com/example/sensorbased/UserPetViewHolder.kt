package com.example.sensorbased

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class UserPetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val user_name_text_view = itemView.findViewById<TextView>(R.id.user_name_text_view)
    val pet_name_text_view = itemView.findViewById<TextView>(R.id.pet_name_text_view)


}