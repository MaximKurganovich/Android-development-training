package com.example.moshi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moshi.databinding.DetailFragmentBinding
import com.example.moshi.networking.movie.Movie
import com.example.moshi.networking.movie.MovieRating
import com.squareup.moshi.Moshi
import kotlin.random.Random

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private var binding: DetailFragmentBinding? = null
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by activityViewModels()
    private lateinit var scores: Map<String, String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Glide.with(binding!!.poster).load(args.movie.poster).diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).placeholder(R.drawable.empty_avatar).into(binding!!.poster)

        binding!!.title.text = args.movie.title
        binding!!.yearOfIssue.text = "${resources.getString(R.string.year_of_issue)}: ${args.movie.year}"
        selectionOfRatingAndEvaluationOfTheFilm()
        binding!!.generateScore.setOnClickListener { rate() }
    }

    private fun selectionOfRatingAndEvaluationOfTheFilm() {
        val ratingSelectionAdapter = context?.let {
            ArrayAdapter(
                it,
                R.layout.dropdown_menu_popup_item,
                MovieRating.values()
            )
        }
        binding!!.ratingSpinner.apply {
            adapter = ratingSelectionAdapter
        }
        val evaluationSourceSelectionAdapter = context?.let {
            ArrayAdapter(
                it,
                R.layout.dropdown_menu_popup_item,
                resources.getStringArray(R.array.source_of_assessment)
            )
        }
        binding!!.sourceOfAssessmentSpinner.apply {
            adapter = evaluationSourceSelectionAdapter
        }
    }

    private fun rate() {
        viewModel.addRating(args.position, binding!!.ratingSpinner.selectedItem.toString())

        val ratingSourceType = resources.getStringArray(R.array.source_of_assessment)
        val score: Array <String> = arrayOf(((Random.nextInt(10, 100)).toDouble() / 10).toString(),
            "${Random.nextInt(0, 100)}%",
            "${Random.nextInt(0, 100)}/100")
        scores = ratingSourceType.zip(score).toMap()
        viewModel.addRating(args.position, scores)

        addRatingToTextView()

        binding!!.sourceOfAssessmentSpinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addRatingToTextView()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        convertMovieWithScoreInstanceToJson()
    }

    private fun addRatingToTextView() {
        binding!!.score.text = when(binding!!.sourceOfAssessmentSpinner.selectedItem.toString()) {
            resources.getStringArray(R.array.source_of_assessment)[0] -> scores[resources.getStringArray(R.array.source_of_assessment)[0]]
            resources.getStringArray(R.array.source_of_assessment)[1] -> scores[resources.getStringArray(R.array.source_of_assessment)[1]]
            resources.getStringArray(R.array.source_of_assessment)[2] -> scores[resources.getStringArray(R.array.source_of_assessment)[2]]
            else -> ""
        }
    }

    private fun convertMovieWithScoreInstanceToJson() {
        val movieToSerialize = viewModel.getMoviesLiveData().value?.get(args.position)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Movie::class.java).nonNull()

        try {
            val movieToJSON = adapter.toJson(movieToSerialize)
            println(movieToJSON)
        } catch (e: Exception){
            println("parse error = ${e.message}")
        }
    }
}