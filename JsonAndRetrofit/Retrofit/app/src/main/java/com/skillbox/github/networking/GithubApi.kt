package com.skillbox.github.networking


import com.skillbox.github.ui.current_user.UserInformation
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("user")
    fun viewingUserInformation (): Call<List<UserInformation>>
}