package com.skillbox.a11homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_app.*

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = FragmentArticleAdapter(screens, this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
    }

    private val screens: List<DataForFragmentArticle> = listOf(
        DataForFragmentArticle(
            textRes = R.string.how_to_make_water_stop_conducting_electricity,
            imageRes = R.drawable.how_to_make_water_stop_conducting_electricity
        ),
        DataForFragmentArticle(
            textRes = R.string.how_to_see_the_back_of_your_head_with_a_black_hole,
            imageRes = R.drawable.how_to_see_the_back_of_your_head_with_a_black_hole
        ),
        DataForFragmentArticle(
            textRes = R.string.the_paper_folding_myth,
            imageRes = R.drawable.the_paper_folding_myth
        ),
        DataForFragmentArticle(
            textRes = R.string.physics_of_car_accidents,
            imageRes = R.drawable.physics_of_car_accidents
        ),
        DataForFragmentArticle(
            textRes = R.string.how_does_the_world_see_an_object_flying_at_the_speed_of_light,
            imageRes = R.drawable.how_does_the_world_see_an_object_flying_at_the_speed_of_light
        ),
        DataForFragmentArticle (
            textRes = R.string.can_people_feel_the_temperature,
            imageRes = R.drawable.can_people_feel_the_temperature
        ),
        DataForFragmentArticle(
            textRes = R.string.why_do_leaves_change_color,
            imageRes = R.drawable.why_do_leaves_change_color
        ),
        DataForFragmentArticle(
            textRes = R.string.how_to_prove_that_we_do_not_live_in_the_matrix,
            imageRes = R.drawable.how_to_prove_that_we_do_not_live_in_the_matrix
        ),
        DataForFragmentArticle(
            textRes = R.string.supercapacitors_batteries_of_the_future,
            imageRes = R.drawable.supercapacitors_batteries_of_the_future
        )
    )

}