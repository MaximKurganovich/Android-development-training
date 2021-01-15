package com.example.coroutines.ui.repository_list.detail_information

import com.example.coroutines.networking.Networking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository {

    suspend fun getDetailRepo(
        owner: String,
        repo: String,
    ): DetailRepo {
        return withContext(Dispatchers.IO) { Networking.githubApi.detailRepo(owner, repo) }
    }

    suspend fun checkStar(
        owner: String, repo: String
    ): Boolean {
        return suspendCancellableCoroutine { continuation ->
            val call = Networking.githubApi.checkStar(owner, repo)
            continuation.invokeOnCancellation {
                call.cancel()
            }
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    when (response.code()) {
                        CODE_IS_SUCCESS -> ResponseFromServer.SuccessStar(true)
                        CODE_IS_NOT_SUCCESS -> ResponseFromServer.SuccessStar(false)
                        else -> ResponseFromServer.Error("Что-то пошло не так, код ответа сервера ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    ResponseFromServer.Error("Что-то пошло не так ${t.message}")
                }
            })
        }
    }

    suspend fun addStar(
        owner: String, repo: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            when (Networking.githubApi.addStar(owner, repo).code()) {
                CODE_IS_SUCCESS -> true
                else -> false
            }
        }
    }

    suspend fun removeStar(
        owner: String, repo: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            when (Networking.githubApi.removeStar(owner, repo).code()) {
                CODE_IS_SUCCESS -> true
                else -> false
            }
        }
    }

    companion object {
        private const val CODE_IS_SUCCESS = 204
        private const val CODE_IS_NOT_SUCCESS = 404
    }
}