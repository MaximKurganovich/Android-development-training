package com.skillbox.github.ui.repository_list

import com.skillbox.github.networking.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesInformation {

    fun getRepository(
        since: Int,
        onComplete: (List<RepositoryInformation>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Networking.githubApi.getRepositories(since).enqueue(
            object : Callback<List<RepositoryInformation>> {
                override fun onResponse(
                    call: Call<List<RepositoryInformation>>,
                    response: Response<List<RepositoryInformation>>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body().orEmpty())
                    } else {
                        onError(RuntimeException("incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<List<RepositoryInformation>>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }


}