package com.example.coroutines.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserInformationViewModel: ViewModel() {

    private val repository = UserInformationRepository()
    private val userListLiveData = MutableLiveData<UserInformation>()
    val userList: LiveData<UserInformation> = userListLiveData

    private val followingListLiveData = MutableLiveData<List<FollowingInformation>>()
    val followingList: LiveData<List<FollowingInformation>> = followingListLiveData

    private val showErrorDialogLiveData = SingleLiveEvent<String>()
    val showErrorDialog: LiveData<String> = showErrorDialogLiveData

    fun userInformation() {
        viewModelScope.launch(errorHandler) {
            val userDeferred = async { repository.userInformation() }
            val followingDeferred = async { repository.getFollowing() }

            val userResult = userDeferred.await()
            val followingResult = followingDeferred.await()

            userListLiveData.postValue(userResult)
            followingListLiveData.postValue(followingResult)
        }
    }

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        showErrorDialogLiveData.postValue(throwable.message)
    }
}