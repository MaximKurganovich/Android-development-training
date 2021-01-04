package com.skillbox.github.ui.repository_list.detail_information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.utils.SingleLiveEvent
import okhttp3.Call

class DetailViewModel : ViewModel() {
    private val detailListLiveData = MutableLiveData<DetailRepo>()
    val detailRepo: LiveData<DetailRepo> = detailListLiveData

    private val showErrorDialogLiveData = SingleLiveEvent<String>()
    val showErrorDialog: LiveData<String> = showErrorDialogLiveData

    private val isStarredLiveData = MutableLiveData<Boolean>()
    val isStarred: LiveData<Boolean> = isStarredLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private var currentCall: Call? = null

    private val repository = DetailRepository()

    fun getDetailRepo(owner: String, repo: String) {
        isLoadingLiveData.postValue(true)
        repository.getDetailRepo(owner, repo, onComplete = {
            isLoadingLiveData.postValue(false)
            detailListLiveData.postValue(it.result)
        }, onError = { showErrorDialogLiveData.postValue(it.message) })
        currentCall = null
    }

    fun checkStar(owner: String, repo: String) {
        isLoadingLiveData.postValue(true)
        repository.checkStar(owner, repo,
            onComplete = {
                isLoadingLiveData.postValue(false)
                isStarredLiveData.postValue(it.success)
            },
            onError = { showErrorDialogLiveData.postValue(it.message) }
        )
        currentCall = null
    }

    fun starred(owner: String, repo: String) {
        isLoadingLiveData.postValue(true)
        if (isStarred.value == true) {
            repository.removeStar(owner, repo, onComplete = {
                if (it.success) {
                    isLoadingLiveData.postValue(false)
                    isStarredLiveData.postValue(it.success.not())
                }
            }, onError = { showErrorDialogLiveData.postValue(it.message) })
        } else {
            isLoadingLiveData.postValue(true)
            repository.addStar(owner, repo, onComplete = {
                isLoadingLiveData.postValue(false)
                if (it.success) {
                    isStarredLiveData.postValue(it.success)
                }
            }, onError = { showErrorDialogLiveData.postValue(it.message) })
        }
        currentCall = null
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}

sealed class ResponseFromServer {
    data class SuccessfulReceiptOfInfo(val result: DetailRepo) : ResponseFromServer()
    data class Error(val message: String) : ResponseFromServer()
    data class SuccessStar(val success: Boolean) : ResponseFromServer()
}