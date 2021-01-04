package com.skillbox.github.ui.repository_list.detail_information

import com.skillbox.github.networking.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository {

    fun getDetailRepo(
        owner: String,
        repo: String,
        onComplete: (ResponseFromServer.SuccessfulReceiptOfInfo) -> Unit,
        onError: (ResponseFromServer.Error) -> Unit
    ) {
        Networking.githubApi.detailRepo(owner, repo).enqueue(
            object : Callback<DetailRepo> {
                override fun onResponse(call: Call<DetailRepo>, response: Response<DetailRepo>) {
                    if (response.isSuccessful) {
                        onComplete(ResponseFromServer.SuccessfulReceiptOfInfo(response.body()!!))
                    } else {
                        onError(ResponseFromServer.Error("Ошибка при получении данных. Код ошибки ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<DetailRepo>, t: Throwable) {
                    onError(ResponseFromServer.Error("Ошибка соединения - ${t.message}"))
                }
            }
        )
    }

    fun checkStar(
        owner: String, repo: String,
        onComplete: (ResponseFromServer.SuccessStar) -> Unit,
        onError: (ResponseFromServer.Error) -> Unit
    ) {
        Networking.githubApi.checkStar(owner, repo).enqueue(
            object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    when (response.code()) {
                        CODE_IS_SUCCESS -> onComplete(ResponseFromServer.SuccessStar(true))
                        CODE_IS_NOT_SUCCESS -> onComplete(ResponseFromServer.SuccessStar(false))
                        else -> onError(ResponseFromServer.Error("Что-то пошло не так, код ответа сервера ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    onError(ResponseFromServer.Error("Что-то пошло не так ${t.message}"))
                }
            }
        )
    }

    fun addStar(
        owner: String, repo: String,
        onComplete: (ResponseFromServer.SuccessStar) -> Unit,
        onError: (ResponseFromServer.Error) -> Unit
    ) {
        Networking.githubApi.addStar(owner, repo).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                when (response.code()) {
                    CODE_IS_SUCCESS -> onComplete(ResponseFromServer.SuccessStar(true))
                    else -> onComplete(ResponseFromServer.SuccessStar(false))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                onError(ResponseFromServer.Error("Что-то пошло не так ${t.message}"))
            }
        })
    }

    fun removeStar(
        owner: String, repo: String,
        onComplete: (ResponseFromServer.SuccessStar) -> Unit,
        onError: (ResponseFromServer.Error) -> Unit
    ) {
        Networking.githubApi.removeStar(owner, repo).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                when (response.code()) {
                    CODE_IS_SUCCESS -> onComplete(ResponseFromServer.SuccessStar(true))
                    else -> onComplete(ResponseFromServer.SuccessStar(false))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                onError(ResponseFromServer.Error("Что-то пошло не так ${t.message}"))
            }
        })
    }

    companion object {
        private const val CODE_IS_SUCCESS = 204
        private const val CODE_IS_NOT_SUCCESS = 404
    }
}