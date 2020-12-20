package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInformationViewModel: ViewModel() {

    private val repository = UserInformationRepository()
    private val userListLiveData = MutableLiveData<List<UserInformation>>(emptyList())

    val userList: LiveData<List<UserInformation>>
        get() = userListLiveData

    fun userInformation() {
        repository.userInformation(
            onComplete = {user -> userListLiveData.postValue(user)},
            onError = {throwable -> userListLiveData.postValue(emptyList())}
        )
    }
}