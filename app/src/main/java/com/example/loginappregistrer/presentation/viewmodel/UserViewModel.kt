package com.example.loginappregistrer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginappregistrer.domain.usecase.GetUsersCase
import com.example.loginappregistrer.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersCase: GetUsersCase
) : ViewModel() {

    private val eventMutableLiveData = MutableLiveData<UserEvent>()
    val event: LiveData<UserEvent> get() = eventMutableLiveData

    fun getUsers() {
        viewModelScope.launch {
            eventMutableLiveData.value = UserEvent.ShowLoading
            when (val users = getUsersCase.invoke()) {
                is Result.Success -> {
                    eventMutableLiveData.value = UserEvent.SetUsers(users.value)
                }
                is Result.Error -> eventMutableLiveData.value = UserEvent.ShowError
            }

        }

    }
}
