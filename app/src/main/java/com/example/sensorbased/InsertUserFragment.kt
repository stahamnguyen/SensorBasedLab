package com.example.sensorbased

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sensorbased.model.User
import com.example.sensorbased.model.UserPetDB
import kotlinx.android.synthetic.main.fragment_insert_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertUserFragment : Fragment() {
    private lateinit var db: UserPetDB

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
        val view = inflater.inflate(R.layout.fragment_insert_user, container, false)
        val insertButton = view.findViewById<Button>(R.id.button_insert);
        insertButton.setOnClickListener {
            val username = text_field_user.text.toString()
            GlobalScope.launch {
                val id = db.userDao().insert(User(name = username))
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance() = InsertUserFragment()
    }
}