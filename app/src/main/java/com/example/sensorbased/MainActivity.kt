package com.example.sensorbased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensorbased.model.GlobalModel
import com.example.sensorbased.model.President
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finnish_presidents_recycler_view.layoutManager = LinearLayoutManager(this)
        finnish_presidents_recycler_view.adapter = FinnishPresidentsRecyclerViewAdapter(GlobalModel.presidents)
    }
}