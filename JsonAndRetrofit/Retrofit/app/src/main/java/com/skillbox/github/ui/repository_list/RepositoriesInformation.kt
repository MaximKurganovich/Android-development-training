package com.skillbox.github.ui.repository_list

import com.skillbox.github.networking.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesInformation {

    fun getRepository(
        sort: String,
        onComplete: (List<RepositoryInformation>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Networking.githubApi.getRepositories(sort).enqueue(
            object : Callback<RepositoryInformation> {
                override fun onResponse(
                    call: Call<RepositoryInformation>,
                    response: Response<RepositoryInformation>
                ) {
                    if (response.isSuccessful) {
                        val list = listOf(response.body()) as List<RepositoryInformation>
                        onComplete(list)
                    } else {
                        onError(RuntimeException("incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RepositoryInformation>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}