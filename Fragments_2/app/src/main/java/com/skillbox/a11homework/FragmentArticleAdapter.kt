package com.skillbox.a11homework

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentArticleAdapter(
    private val screens: List<DataForFragmentArticle>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        val screen = screens[position]
        return FragmentArticle.newInstance(
            textRes = screen.textRes,
            drawableRes = screen.imageRes
        )
    }
}