package com.example.coroutines.ui.repository_list.detail_information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val detailListLiveData = MutableLiveData<DetailRepo>()
    val detailRepo: LiveData<DetailRepo> = detailListLiveData

    private val showErrorDialogLiveData = SingleLiveEvent<String>()
    val showErrorDialog: LiveData<String> = showErrorDialogLiveData

    private val isStarredLiveData = MutableLiveData<Boolean>()
    val isStarred: LiveData<Boolean> = isStarredLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private val repository = DetailRepository()

    fun getDetailRepo(owner: String, repo: String) {
        isLoadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                val detailRepo = repository.getDetailRepo(owner, repo)
                isLoadingLiveData.postValue(false)
                detailListLiveData.postValue(detailRepo)
            } catch (t: Throwable) {
                showErrorDialogLiveData.postValue(t.message)
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }

    fun checkStar(owner: String, repo: String) {
        isLoadingLiveData.postValue(true)
        viewModelScope.launch(errorHandler) {
            val result = repository.checkStar(owner, repo)
            isStarredLiveData.postValue(result)
        }
    }

    fun starred(owner: String, repo: String) {
        isLoadingLiveData.postValue(true)
        viewModelScope.launch(errorHandler) {
            if (isStarred.value == true) {
                repository.removeStar(owner, repo)
            } else {
                isStarredLiveData.postValue(repository.addStar(owner, repo))
                isLoadingLiveData.postValue(false)
            }
        }
    }

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        showErrorDialogLiveData.postValue(throwable.message)
    }

}

sealed class ResponseFromServer {
    data class SuccessfulReceiptOfInfo(val result: DetailRepo) : ResponseFromServer()
    data class Error(val message: String) : ResponseFromServer()
    data class SuccessStar(val success: Boolean) : ResponseFromServer()
}