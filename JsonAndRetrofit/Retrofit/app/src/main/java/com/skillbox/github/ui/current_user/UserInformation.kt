package com.skillbox.github.ui.current_user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInformation(
        @Json(name = "login")
        val name: String,
        val url: String,
        @Json(name = "avatar_url")
        val avatar: String?,
        val type: String
)
