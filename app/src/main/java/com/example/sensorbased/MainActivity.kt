package com.example.sensorbased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensorbased.model.User
import com.example.sensorbased.model.UserAndAllPets
import com.example.sensorbased.viewmodel.PetModel
import com.example.sensorbased.viewmodel.UserModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userViewModel = ViewModelProviders.of(this).get(UserModel::class.java)
        userViewModel.getUsers().observe(this, {
            it.forEach { user ->
                (recycler_view.adapter as UserPetRecyclerViewAdapter).updateUser(UserAndAllPets(user, mutableListOf()))
            }
            recycler_view.adapter?.notifyDataSetChanged()
        })

        val petViewModel = ViewModelProviders.of(this).get(PetModel::class.java)
        petViewModel.getPets().observe(this, {
            println(it.size)
            it.forEach { pet ->
                (recycler_view.adapter as UserPetRecyclerViewAdapter).updatePet(pet)
            }
            recycler_view.adapter?.notifyDataSetChanged()
        })

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = UserPetRecyclerViewAdapter(mutableListOf<UserAndAllPets>())
    }
}