package com.skillbox.a11homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_app.*
import kotlin.random.Random

class AppActivity : AppCompatActivity(R.layout.activity_app),
    DialogChoiceFragment.OnCompleteListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val adapter = FragmentArticleAdapter(screens, this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1

        addIndicator()
        addAnimation()
        addTab()

        button.setOnClickListener { generateBadge(screens) }

        //Удаляет бейдж при переходе на вкладку
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })

    }

    //Добавляет индикатор для viewPager
    private fun addIndicator() {
        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        wormDotsIndicator.setViewPager2(viewPager)
    }

    //Добавляет анимацию для viewPager
    private fun addAnimation() {
        val depthTransformation = DepthTransformation()
        viewPager.setPageTransformer(depthTransformation)
    }

    //Связывает tabLayout и viewPager и добавляет вкладка имена
    private fun addTab() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    //Создает случайный бейдж
    private fun generateBadge(list: List<DataForFragmentArticle>) {
        tabLayout.getTabAt(Random.nextInt(0, list.size))?.orCreateBadge?.apply {
            number++
            badgeGravity = BadgeDrawable.TOP_END
        }
    }

    //Метод принимает список отфльтрованных статей и обновляет viewPager
    override fun onComplete(list: List<DataForFragmentArticle>) {
        val articleAdapter = FragmentArticleAdapter(list, this)
        viewPager.adapter = articleAdapter
        worm_dots_indicator.setViewPager2(viewPager)
        addTab()
        button.setOnClickListener { generateBadge(list) }
    }


    fun getScreens(): List<DataForFragmentArticle> {
        return screens
    }

    private val screens: List<DataForFragmentArticle> = listOf(
        DataForFragmentArticle(
            textRes = R.string.how_to_make_water_stop_conducting_electricity,
            imageRes = R.drawable.how_to_make_water_stop_conducting_electricity,
            tag = ArticleTag.usefulFacts.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.how_to_see_the_back_of_your_head_with_a_black_hole,
            imageRes = R.drawable.how_to_see_the_back_of_your_head_with_a_black_hole,
            tag = ArticleTag.usefulFacts.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.the_paper_folding_myth,
            imageRes = R.drawable.the_paper_folding_myth,
            tag = ArticleTag.usefulFacts.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.physics_of_car_accidents,
            imageRes = R.drawable.physics_of_car_accidents,
            tag = ArticleTag.interestingFacts.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.how_does_the_world_see_an_object_flying_at_the_speed_of_light,
            imageRes = R.drawable.how_does_the_world_see_an_object_flying_at_the_speed_of_light,
            tag = ArticleTag.interestingFacts.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.can_people_feel_the_temperature,
            imageRes = R.drawable.can_people_feel_the_temperature,
            tag = ArticleTag.interestingFacts.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.why_do_leaves_change_color,
            imageRes = R.drawable.why_do_leaves_change_color,
            tag = ArticleTag.mythsAndReality.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.how_to_prove_that_we_do_not_live_in_the_matrix,
            imageRes = R.drawable.how_to_prove_that_we_do_not_live_in_the_matrix,
            tag = ArticleTag.mythsAndReality.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.supercapacitors_batteries_of_the_future,
            imageRes = R.drawable.supercapacitors_batteries_of_the_future,
            tag = ArticleTag.mythsAndReality.tag
        )
    )


}