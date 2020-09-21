package com.example.sensorbased

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sensorbased.model.Pet
import com.example.sensorbased.model.User
import com.example.sensorbased.model.UserAndAllPets

class UserPetRecyclerViewAdapter(private val data: MutableList<UserAndAllPets>) : RecyclerView.Adapter<UserPetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_user_pet, parent, false)
        view.post {
            view.layoutParams.height = parent.height / 8
            view.requestLayout()
        }
        return UserPetViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserPetViewHolder, position: Int) {
        holder.user_name_text_view.text = data[position].user.name ?: "No owner name"
        holder.pet_name_text_view.text = data[position].pets?.map { pet -> pet.name }
            ?.joinToString { name -> name }
            ?: "No pet"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateUser(combination: UserAndAllPets) {
        val existingUsernames = data.map { it.user.name }

        if (!existingUsernames.contains(combination.user.name)) {
            data.add(combination)
        }
    }

    fun updatePet(pet: Pet) {
        val existingUsernames = data.map { it.user.name }
        if (existingUsernames.contains(pet.ownerName)) {
            val petsOfThatUser = data.first { it.user.name == pet.ownerName }.pets
            println(petsOfThatUser)
            println(pet.name)
            if (!petsOfThatUser.contains(pet.name)) {
                petsOfThatUser.add(pet)
            }
        }
    }
}