package com.skillbox.multithreading.threading

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.multithreading.R
import com.skillbox.multithreading.adapter.FilmAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.fragment_threading.*

class ThreadingFragment : Fragment(R.layout.fragment_threading) {

    //    Создаются:
//    1. Поле, в котором хранятся данные для списка
//    2. Адаптер
//    3. Handler.
    private val viewModel: MovieViewModel by viewModels()
    private var filmAdapter: FilmAdapter? = null
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        requestMovies.setOnClickListener { getInfoAboutFilms() }
    }

    override fun onDestroy() {
        super.onDestroy()
        filmAdapter = null
    }

    //    Адаптеру присваиваются атрибуты и указывается тип LayoutManager
    private fun initList() {
        filmAdapter = FilmAdapter()
        with(recycleView) {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
    }

//    Метод устанавливает через Handler список в адаптер
    private fun getInfoAboutFilms() {
        viewModel.requestMovies { movies -> mainHandler.post { filmAdapter?.items = movies } }
//    Через секунду отображается ост
        mainHandler.postDelayed({
            Toast.makeText(
                requireContext(),
                "Фильмы загружены",
                Toast.LENGTH_SHORT
            ).show()
        }, 1000)
    }
}