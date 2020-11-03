package com.skillbox.multithreading.threading

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.multithreading.R
import com.skillbox.multithreading.adapter.FilmAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.fragment_threading.*

class ThreadingFragment : Fragment(R.layout.fragment_threading) {

    private val viewModel: MovieViewModel by viewModels()

    private var filmAdapter: FilmAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        requestMovies.setOnClickListener { getInfoAboutFilms() }
    }

    override fun onDestroy() {
        super.onDestroy()
        filmAdapter = null
    }

    private fun initList() {
        filmAdapter = FilmAdapter()
        with(recycleView) {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
    }

    private fun getInfoAboutFilms() {
        viewModel.requestMovies()
        viewModel.movies.observe(viewLifecycleOwner) { newList ->
            filmAdapter?.items = newList
        }
    }

}