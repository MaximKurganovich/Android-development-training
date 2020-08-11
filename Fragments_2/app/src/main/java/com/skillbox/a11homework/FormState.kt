package com.skillbox.a11homework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState (
    var saveList: List<String>): Parcelable {
}