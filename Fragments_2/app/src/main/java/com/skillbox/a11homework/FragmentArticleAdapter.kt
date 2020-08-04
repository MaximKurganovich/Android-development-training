package com.skillbox.a11homework

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentArticleAdapter(
    private val screens: List<DataForFragmentArticle>,
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen = screens[position]
        return FragmentArticle.newInstance(
            textRes = screen.textRes,
            drawableRes = screen.imageRes
        )
    }
}