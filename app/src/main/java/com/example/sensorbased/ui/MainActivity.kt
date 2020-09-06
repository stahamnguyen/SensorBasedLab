package com.example.sensorbased.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensorbased.FinnishPresidentRecyclerViewClickListener
import com.example.sensorbased.FinnishPresidentsRecyclerViewAdapter
import com.example.sensorbased.R
import com.example.sensorbased.model.GlobalModel
import com.example.sensorbased.model.President
import com.example.sensorbased.repository.WikiServiceRepo
import com.example.sensorbased.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FinnishPresidentRecyclerViewClickListener {
  lateinit var viewModel: MainViewModel
  private val repo: WikiServiceRepo = WikiServiceRepo()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    viewModel.selectedPresident.observe(this, Observer { president ->
      president_info_text_view.text = president.toString()
      president_description_text_view.text = president.description

      repo.getNumberOfLinks(president.name) {
        hits_text_view.text = it?.totalhits.toString()
      }
    })

    finnish_presidents_recycler_view.layoutManager = LinearLayoutManager(this)
    finnish_presidents_recycler_view.adapter = FinnishPresidentsRecyclerViewAdapter(GlobalModel.presidents, this)
  }

  override fun presidentItemClicked(president: President) {
    viewModel.selectedPresident.value = president
  }
}
