package com.example.loginappregistrer.presentation.ui.UserDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginappregistrer.data.remote.datasource.UserRemoteDataSource
import com.example.loginappregistrer.data.remote.model.UserResponse
import com.example.loginappregistrer.domain.model.User
import com.example.loginappregistrer.domain.usecase.GetUsersCase
import com.example.loginappregistrer.presentation.viewmodel.UserEvent
import com.example.loginappregistrer.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUsersCase: GetUsersCase
) : ViewModel() {

    private val _logRegisterRepositoryImp = UserRemoteDataSource()

    private val eventMutableLiveData = MutableLiveData<UserEvent>()
    val event: LiveData<UserEvent> get() = eventMutableLiveData

    fun userBalance(username: String): Int {
        var mySaldo = 0
        viewModelScope.launch {
            when (val users = getUsersCase.invoke()) {
                is Result.Success -> {
                    users.value.map { value ->
                        if (value.userName == username) {
                            var saldo: List<UserResponse> = getBalance(value.id)
                            saldo.map { saldo ->
                                mySaldo = saldo.saldo
                            }
                        }
                    }
                }
                is Result.Error -> {
                    eventMutableLiveData.value = UserEvent.ShowError
                }
            }
        }
        return  mySaldo
    }

    private suspend fun getBalance(id: Int): List<UserResponse> {
        return _logRegisterRepositoryImp.logRegisterRepositoryApi.getSaldo(id)
    }

}
