package com.skillbox.a14homework.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.for_grid_layout_manager.*

/*
* Смотри {@link PlanetAdapterDelegate}
*/
class ImageGridAdapterDelegate() :
    AbsListItemAdapterDelegate<CelestialBodies.ImagePlanet, CelestialBodies, ImageGridAdapterDelegate.ImageGridHolder>() {

    override fun isForViewType(
        item: CelestialBodies,
        items: MutableList<CelestialBodies>,
        position: Int
    ): Boolean {
        return item is CelestialBodies.ImagePlanet
    }

    override fun onCreateViewHolder(parent: ViewGroup): ImageGridHolder {
        return ImageGridHolder(parent.inflate(R.layout.for_grid_layout_manager))
    }

    override fun onBindViewHolder(
        item: CelestialBodies.ImagePlanet,
        holder: ImageGridHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ImageGridHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(linkToImage: CelestialBodies.ImagePlanet) {
            Glide.with(itemView).load(linkToImage.avatarLink).placeholder(R.drawable.empty_avatar)
                .into(imageAvatar)
        }
    }
}
