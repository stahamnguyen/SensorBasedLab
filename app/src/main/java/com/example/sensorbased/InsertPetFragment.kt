package com.example.sensorbased

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Spinner
import com.example.sensorbased.model.Pet
import com.example.sensorbased.model.User
import com.example.sensorbased.model.UserPetDB
import kotlinx.android.synthetic.main.fragment_insert_pet.*
import kotlinx.android.synthetic.main.fragment_insert_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sensorbased.model.UserAndAllPets
import com.example.sensorbased.viewmodel.UserModel
import kotlinx.android.synthetic.main.activity_main.*

class InsertPetFragment : Fragment() {
    private lateinit var db: UserPetDB
    private val usernames: MutableList<String> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = UserPetDB.get(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_insert_pet, container, false)

        val userSpinner = view.findViewById<Spinner>(R.id.user_spinner);
        val spinnerAdapter = ArrayAdapter(this.requireContext(), R.layout.spinner_item, usernames)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)
        userSpinner.adapter = spinnerAdapter

        val viewModel = ViewModelProviders.of(this).get(UserModel::class.java)
        viewModel.getUsers().observe(this, Observer { it ->
            val fetchedUsernames = it.toTypedArray().map { user -> user.name }
            fetchedUsernames.forEach { fetchedUsername -> usernames.add(fetchedUsername) }
            (userSpinner.adapter as ArrayAdapter<*>).notifyDataSetChanged()
        })

        val insertButton = view.findViewById<Button>(R.id.button_insert);
        insertButton.setOnClickListener {
            view.let { (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0) }
            val petName = text_field_pet.text.toString()
            val username = userSpinner.selectedItem.toString()
            GlobalScope.launch {
                val id = db.petDao().insert(Pet(name = petName, ownerName = username))
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance(): InsertPetFragment {
            return InsertPetFragment()
        }
    }
}