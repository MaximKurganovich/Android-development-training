package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepositoryViewModel : ViewModel() {

    private val repositoryListLiveData = MutableLiveData<List<RepositoryInformation>>(emptyList())
    private val repository = RepositoriesInformation()

    val repositoryList: LiveData<List<RepositoryInformation>>
        get() = repositoryListLiveData

    fun getRepository(sort: String) {
        repository.getRepository(
            sort,
            onComplete = {repository -> repositoryListLiveData.postValue(repository)},
            onError = {repositoryListLiveData.postValue(emptyList())}
        )
    }
}