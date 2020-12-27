package com.skillbox.github.networking


import com.skillbox.github.ui.current_user.UserInformation
import com.skillbox.github.ui.repository_list.RepositoryInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("user")
    fun viewingUserInformation (): Call<UserInformation>

    @GET("user/repos")
    fun getRepositories(
        @Query("sort") sort: String
    ): Call<RepositoryInformation>
}