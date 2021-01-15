package com.example.coroutines.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class RepositoryViewModel : ViewModel() {

    private val repositoryListLiveData = MutableLiveData<List<RepositoryInformation>>()
    private val repository = RepositoriesInformation()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val repositoryList: LiveData<List<RepositoryInformation>>
        get() = repositoryListLiveData


    fun getRepository() {
        scope.launch {
            try {
                val repositories = repository.getRepository()
                repositoryListLiveData.postValue(repositories)
            } catch (t: Throwable) {
                repositoryListLiveData.postValue(emptyList())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}