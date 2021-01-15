package com.example.coroutines.networking


import com.example.coroutines.ui.current_user.FollowingInformation
import com.example.coroutines.ui.current_user.UserInformation
import com.example.coroutines.ui.repository_list.RepositoryInformation
import com.example.coroutines.ui.repository_list.detail_information.DetailRepo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {

    @GET("user")
    suspend fun viewingUserInformation(): UserInformation

    @GET("user/following")
    suspend fun getFollowing(): List<FollowingInformation>

    @GET("repositories")
    fun getRepositories(): Call<List<RepositoryInformation>>

    @GET("repos/{owner}/{repo}")
    suspend fun detailRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): DetailRepo

    @GET("user/starred/{owner}/{repo}")
    fun checkStar(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>

    @PUT("user/starred/{owner}/{repo}")
    suspend fun addStar(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Boolean>

    @DELETE("user/starred/{owner}/{repo}")
    suspend fun removeStar(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Boolean>
}