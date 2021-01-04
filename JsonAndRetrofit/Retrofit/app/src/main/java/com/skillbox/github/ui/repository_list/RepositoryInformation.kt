package com.skillbox.github.ui.repository_list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryInformation(
    var name: String,
    @Json(name = "full_name")
    var fullName: String,
    var id: Int,
    var owner: Owner
)

@JsonClass(generateAdapter = true)
data class Owner(
    var login: String,
    var type: String,
    @Json(name = "avatar_url")
    var avatar: String,
)
