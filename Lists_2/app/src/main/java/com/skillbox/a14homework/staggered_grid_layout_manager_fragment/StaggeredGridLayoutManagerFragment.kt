package com.skillbox.a14homework.staggered_grid_layout_manager_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.adapters.ListAdapter
import kotlinx.android.synthetic.main.for_grid_layout_manager.*
import kotlinx.android.synthetic.main.list_of_celestial_bodies_fragment.*

// Смотри GridLayoutManagerFragment
class StaggeredGridLayoutManagerFragment : Fragment(R.layout.list_of_celestial_bodies_fragment) {

    private val celestialBodies = generateList(1000)

    private var staggeredGridLayoutManagerAdapter: ListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addFab.visibility = View.GONE
        initList()
        staggeredGridLayoutManagerAdapter?.items = celestialBodies
    }

    override fun onDestroy() {
        super.onDestroy()
        staggeredGridLayoutManagerAdapter = null
    }

    private fun initList() {
        staggeredGridLayoutManagerAdapter = ListAdapter { }
        with(recycleViewCelestialBodies) {
            adapter = staggeredGridLayoutManagerAdapter
            addItemDecoration(com.skillbox.a14homework.PaddingBetweenElements(requireContext()))
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
    }

    private fun generateList(count: Int): List<CelestialBodies> {
        val linksToImages = listOf(
            "https://cdni.rt.com/russian/images/2017.06/article/594e4840c46188182a8b473a.jpg",
            "https://cdn25.img.ria.ru/images/149845/35/1498453574_0:155:5200:3099_600x0_80_0_0_be9e92c1e2583ea131b12e6f29118713.jpg.webp",
            "https://profile.ru/wp-content/uploads/2020/05/gigantskaya-planeta-782x440.jpg",
            "https://cdn25.img.ria.ru/images/156141/65/1561416591_0:0:1152:648_600x0_80_0_0_091c669a76aaf5e669b416cad1d7bc98.jpg.webp",
            "https://cdn25.img.ria.ru/images/151694/25/1516942524_0:0:1036:686_600x0_80_0_0_9de43ffa10a7f7f6cd0ece0ab8058040.jpg.webp",
            "https://cdn.spbdnevnik.ru/uploads/block/image/287090/__medium_shutter_planetX_promo.jpg.jpg",
            "https://s.hi-news.ru/wp-content/uploads/2014/06/wallpaper-27182-750x469.jpg",
            "https://hi-news.ru/wp-content/uploads/2014/06/99481817-e1403304550490.jpg",
            "https://hi-news.ru/wp-content/uploads/2014/06/480529561.jpg",
            "https://hi-news.ru/wp-content/uploads/2014/06/800px-Ssc2004-05b.jpg",
            "https://img.joinfo.ua/i/2017/11/o/5a102d20f3114.jpg",
            "https://pics.utro.ru/utro_photos/2020/02/21/1436216.jpg",
            "https://www.dialog.ua/images/news/main_video_widget/8d00fe8e38f6c5b424433b64d4f5b9df.jpg"
        )
        return (0..count).map {
            CelestialBodies.ImagePlanet(
                avatarLink = linksToImages.random()
            )
        }
    }
}