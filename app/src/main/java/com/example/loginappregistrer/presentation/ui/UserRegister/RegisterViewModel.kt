package com.example.loginappregistrer.presentation.ui.UserRegister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginappregistrer.data.remote.datasource.UserRemoteDataSource
import com.example.loginappregistrer.domain.usecase.GetUsersCase
import com.example.loginappregistrer.presentation.viewmodel.UserEvent
import com.example.loginappregistrer.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getUsersCase: GetUsersCase
) : ViewModel() {

    private val eventMutableLiveData = MutableLiveData<UserEvent>()
    val event: LiveData<UserEvent> get() = eventMutableLiveData

    private val _errorToast = MutableLiveData<Boolean>()
    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorPass = MutableLiveData<Boolean>()
    val erroPass: LiveData<Boolean>
        get() = _errorPass

    private val _errorToastUsername = MutableLiveData<Boolean>()
    val errotoastUserName: LiveData<Boolean>
        get() = _errorToastUsername

    private val _doneRegister = MutableLiveData<Boolean>()
    val doneRegister: LiveData<Boolean>
        get() = _doneRegister

    private val _logRegisterRepositoryImp = UserRemoteDataSource()

    fun register(
        firstname: String,
        lastName: String,
        username: String,
        password: String,
        repeat: String
    ) {
        if (repeat != password) {
            _errorPass.value = true
        }
        if (firstname.isNullOrEmpty() || lastName.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty() || repeat.isNullOrEmpty()) {
            _errorToast.value = true
        } else {
            viewModelScope.launch {
                when (val users = getUsersCase.invoke()) {
                    is Result.Success -> {
                        val usersNames = users.value.map { user -> user.userName }
                        if (usersNames.contains(username)) {
                            _errorToastUsername.value = true
                        } else {
                            insert(firstname, lastName, username, password)
                            _doneRegister.value = true
                        }
                    }
                    is Result.Error -> {
                        eventMutableLiveData.value = UserEvent.ShowError
                    }
                }

            }
        }
    }

    fun doneToastUserName() {
        _errorToastUsername.value = false
    }

    fun donetoast() {
        _errorToast.value = false
    }

    fun passError() {
        _errorPass.value = false
    }

    fun doneRegisterexit() {
        _doneRegister.value = false
    }

    private fun insert(
        firstname: String,
        lastName: String,
        userName: String,
        password: String
    ): Job = viewModelScope.launch {
        _logRegisterRepositoryImp.logRegisterRepositoryApi.registerUsers(
            firstname,
            lastName,
            userName,
            password
        )
    }

}
