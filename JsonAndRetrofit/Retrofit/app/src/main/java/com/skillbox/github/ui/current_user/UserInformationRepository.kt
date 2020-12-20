package com.skillbox.github.ui.current_user

import com.skillbox.github.networking.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInformationRepository {

    fun userInformation(
        onComplete: (List<UserInformation>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Networking.githubApi.viewingUserInformation().enqueue(
            object : Callback<List<UserInformation>> {
                override fun onResponse(
                    call: Call<List<UserInformation>>,
                    response: Response<List<UserInformation>>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body().orEmpty())
                        println("ОТВЕТ = ${response.body()}")
                    } else {
                        onError(RuntimeException("incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<List<UserInformation>>, t: Throwable) {
                    onError(t)
                }

            }
        )
    }
}