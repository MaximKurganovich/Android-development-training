package com.skillbox.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment(R.layout.detail_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textForFragment.text = requireArguments().getString(KEY_TEXT)
    }

    companion object {
        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }
}