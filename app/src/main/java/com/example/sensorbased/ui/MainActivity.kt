package com.example.sensorbased.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensorbased.FinnishPresidentRecyclerViewClickListener
import com.example.sensorbased.FinnishPresidentsRecyclerViewAdapter
import com.example.sensorbased.R
import com.example.sensorbased.model.GlobalModel
import com.example.sensorbased.model.President
import com.example.sensorbased.model.WikiService
import com.example.sensorbased.repository.WikiServiceRepo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FinnishPresidentRecyclerViewClickListener {
  private val wikiService: WikiService = WikiService.instance
  private val repo: WikiServiceRepo = WikiServiceRepo(wikiService)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    finnish_presidents_recycler_view.layoutManager = LinearLayoutManager(this)
    finnish_presidents_recycler_view.adapter = FinnishPresidentsRecyclerViewAdapter(GlobalModel.presidents, this)
  }

  override fun presidentItemClicked(president: President) {
    president_info_text_view.text = president.toString()
    president_description_text_view.text = president.description

    repo.getNumberOfLinks(president.name) {
      hits_text_view.text = it?.totalhits.toString()
    }
  }
}
