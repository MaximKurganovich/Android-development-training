package com.skillbox.a11homework

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_article.*

class FragmentArticle : Fragment(R.layout.fragment_article) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "FragmentArticle onCreate, ${hashCode()}")
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("lifecycle", "FragmentArticle onActivityCreated, ${hashCode()}")
        imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))
        textArticle.setText(requireArguments().getInt(KEY_TEXT))

        toolbar.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_button_filter -> {
                    DialogChoiceFragment().show(childFragmentManager, "DialogChoiceFragment")
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "FragmentArticle onDestroy, ${hashCode()}")
    }


    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"

        private val tags: List<ArticleTag> = listOf(
            ArticleTag.INTERESTING_FACTS,
            ArticleTag.MYTHS_AND_REALITY, ArticleTag.USEFUL_FACTS
        )

        fun getTags(): List<ArticleTag> {
            return tags
        }

        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes drawableRes: Int
        ) = FragmentArticle().withArguments {
            putInt(KEY_TEXT, textRes)
            putInt(KEY_IMAGE, drawableRes)
        }
    }
}