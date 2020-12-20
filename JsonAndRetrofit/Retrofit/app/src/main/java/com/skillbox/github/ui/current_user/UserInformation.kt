package com.skillbox.github.ui.current_user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserInformation(
        val name: String,
        val email: String,
        @Json(name = "avatar_url")
        val avatar: String?,
        val location: String
)
