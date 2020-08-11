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

    private val saveList = mutableListOf<String>()
    private var saveState: FormState = FormState(saveList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            saveState = savedInstanceState.getParcelable(KEY_SAVE_LIST)!!
            saveList.clear()
            saveList.addAll(saveState.saveList)
            onComplete(saveList)
        } else {
            val adapter = FragmentArticleAdapter(screens, this)
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 1
            addIndicator()
            addTab()
        }

        addAnimation()
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
    override fun onComplete(list: MutableList<String>) {
        val articlesList = getScreens()
        val filteredArticlesList = mutableListOf<DataForFragmentArticle>()
        if (list.size > 0 && list.size != articlesList.size) {
            for (item in articlesList) {
                if (list.contains(item.tag)) {
                    filteredArticlesList.add(item)
                }
            }
        }
        saveList.clear()
        saveList.apply {
            for (item in filteredArticlesList) {
                add(item.tag)
            }
        }
        val articleAdapter = FragmentArticleAdapter(filteredArticlesList, this)
        viewPager.adapter = articleAdapter
        worm_dots_indicator.setViewPager2(viewPager)
        addTab()
        button.setOnClickListener { generateBadge(filteredArticlesList) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_SAVE_LIST, saveState)
    }


    private fun getScreens(): List<DataForFragmentArticle> {
        return screens
    }

    private val screens: List<DataForFragmentArticle> = listOf(
        DataForFragmentArticle(
            textRes = R.string.how_to_make_water_stop_conducting_electricity,
            imageRes = R.drawable.how_to_make_water_stop_conducting_electricity,
            tag = ArticleTag.USEFUL_FACTS.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.how_to_see_the_back_of_your_head_with_a_black_hole,
            imageRes = R.drawable.how_to_see_the_back_of_your_head_with_a_black_hole,
            tag = ArticleTag.USEFUL_FACTS.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.the_paper_folding_myth,
            imageRes = R.drawable.the_paper_folding_myth,
            tag = ArticleTag.USEFUL_FACTS.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.physics_of_car_accidents,
            imageRes = R.drawable.physics_of_car_accidents,
            tag = ArticleTag.INTERESTING_FACTS.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.how_does_the_world_see_an_object_flying_at_the_speed_of_light,
            imageRes = R.drawable.how_does_the_world_see_an_object_flying_at_the_speed_of_light,
            tag = ArticleTag.INTERESTING_FACTS.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.can_people_feel_the_temperature,
            imageRes = R.drawable.can_people_feel_the_temperature,
            tag = ArticleTag.INTERESTING_FACTS.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.why_do_leaves_change_color,
            imageRes = R.drawable.why_do_leaves_change_color,
            tag = ArticleTag.MYTHS_AND_REALITY.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.how_to_prove_that_we_do_not_live_in_the_matrix,
            imageRes = R.drawable.how_to_prove_that_we_do_not_live_in_the_matrix,
            tag = ArticleTag.MYTHS_AND_REALITY.tag
        ),
        DataForFragmentArticle(
            textRes = R.string.supercapacitors_batteries_of_the_future,
            imageRes = R.drawable.supercapacitors_batteries_of_the_future,
            tag = ArticleTag.MYTHS_AND_REALITY.tag
        )
    )

    companion object {
        private const val KEY_SAVE_LIST = "save list"
    }
}