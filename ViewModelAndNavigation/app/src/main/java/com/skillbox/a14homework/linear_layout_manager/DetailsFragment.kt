package com.skillbox.a14homework.linear_layout_manager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val args: DetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (args.celestianBodies is CelestialBodies.IdAndName) {
            val celestialBodies = args.celestianBodies as CelestialBodies.IdAndName
            nameTextView.text =
                "${fragment.resources.getString(R.string.name_of_a_celestial_body)}: ${celestialBodies.name}"
            idTextView.text = "${fragment.resources.getString(R.string.id)} = ${celestialBodies.id}"
        }
    }
}