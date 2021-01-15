package com.example.coroutines.ui.repository_list

import com.example.coroutines.networking.Networking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoriesInformation {

    suspend fun getRepository(): List<RepositoryInformation>? {
        return withContext(Dispatchers.IO) {
            Networking.githubApi.getRepositories().execute().body()
        }
    }
}