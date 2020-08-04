package com.skillbox.a11homework

import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.skillbox.fragment10.withArguments
import kotlinx.android.synthetic.main.fragment_article.*

class FragmentArticle : Fragment(R.layout.fragment_article) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("viewPager", "FragmentArticle oncreate, ${hashCode()}")
        super.onActivityCreated(savedInstanceState)
        imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))
        textArticle.setText(requireArguments().getInt(KEY_TEXT))
    }


    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"

        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes drawableRes: Int
        ): FragmentArticle {
            return FragmentArticle().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_IMAGE, drawableRes)
            }
        }
    }
}