package com.skillbox.a11homework

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainFragment: Fragment(R.layout.fragment_main) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) addFragmentForArticle()
    }

    private fun addFragmentForArticle () {
        childFragmentManager.beginTransaction().add(R.id.mainFragmentContainer, FragmentArticle())
    }

    private val screens: List<DataForFragmentArticle> = listOf(
        DataForFragmentArticle(
            textRes = R.string.some_text_1,
            imageRes = R.drawable.onboarding_drawable_1
        ),
        DataForFragmentArticle(
            textRes = R.string.some_text_2,
            imageRes = R.drawable.onboarding_drawable_2
        ),
        DataForFragmentArticle(
            textRes = R.string.some_text_3,
            imageRes = R.drawable.onboarding_drawable_3
        ),
        DataForFragmentArticle(
            textRes = R.string.some_text_4,
            imageRes = R.drawable.onboarding_drawable_4
        ),
        DataForFragmentArticle(
            textRes = R.string.some_text_5,
            imageRes = R.drawable.onboarding_drawable_5
        )
    )
}