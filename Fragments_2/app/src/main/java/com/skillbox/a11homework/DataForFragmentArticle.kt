package com.skillbox.a11homework

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DataForFragmentArticle(
    @StringRes val textRes: Int,
    @DrawableRes val imageRes: Int
)