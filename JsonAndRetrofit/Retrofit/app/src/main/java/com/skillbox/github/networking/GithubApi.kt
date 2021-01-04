package com.skillbox.github.networking


import com.skillbox.github.ui.current_user.UserInformation
import com.skillbox.github.ui.repository_list.RepositoryInformation
import com.skillbox.github.ui.repository_list.detail_information.DetailRepo
import retrofit2.Call
import retrofit2.http.*

interface GithubApi {

    @GET("user")
    fun viewingUserInformation(): Call<UserInformation>

    @GET("repositories")
    fun getRepositories(
        @Query("since") sort: Int
    ): Call<List<RepositoryInformation>>

    @GET("repos/{owner}/{repo}")
    fun detailRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<DetailRepo>

    @GET("user/starred/{owner}/{repo}")
    fun checkStar(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>

    @PUT("user/starred/{owner}/{repo}")
    fun addStar(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>

    @DELETE("user/starred/{owner}/{repo}")
    fun removeStar(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>
}