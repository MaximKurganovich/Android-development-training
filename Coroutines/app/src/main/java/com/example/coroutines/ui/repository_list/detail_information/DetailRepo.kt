package com.example.coroutines.ui.repository_list.detail_information

import com.example.coroutines.ui.repository_list.Owner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailRepo(
    var name: String,
    @Json(name = "full_name")
    var fullName: String,
    var id: Int,
    var owner: Owner,
    @Json(name = "stargazers_count")
    var star: Int
)
