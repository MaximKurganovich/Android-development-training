package com.example.coroutines.ui.current_user

import com.example.coroutines.networking.Networking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInformationRepository {

    suspend fun userInformation(): UserInformation {
        return withContext(Dispatchers.IO) {
            Networking.githubApi.viewingUserInformation()
        }
    }

    suspend fun getFollowing(): List<FollowingInformation> {
        return withContext(Dispatchers.IO) {
            Networking.githubApi.getFollowing()
        }
    }
}