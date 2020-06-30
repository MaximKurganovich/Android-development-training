package com.skillbox.homework6

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState (
    var message: String): Parcelable {
}