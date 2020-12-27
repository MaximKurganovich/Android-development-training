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
            object : Callback<UserInformation> {
                override fun onResponse(
                    call: Call<UserInformation>,
                    response: Response<UserInformation>
                ) {
                    if (response.isSuccessful) {
                        val list = listOf(response.body()) as List<UserInformation>
                        onComplete(list)
                    } else {
                        onError(RuntimeException("incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<UserInformation>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}