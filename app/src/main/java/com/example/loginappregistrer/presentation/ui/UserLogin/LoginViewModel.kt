package com.example.loginappregistrer.presentation.ui.UserLogin

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginappregistrer.domain.usecase.GetUsersCase
import com.example.loginappregistrer.presentation.viewmodel.UserEvent
import com.example.loginappregistrer.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUsersCase: GetUsersCase
) : ViewModel() {

    private lateinit var myPreferen: MyPreference

    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context

    private val eventMutableLiveData = MutableLiveData<UserEvent>()
    val event: LiveData<UserEvent> get() = eventMutableLiveData

    private var _navigateToUserDetails = MutableLiveData<Boolean>()
    val navigateToUserDetails: LiveData<Boolean>
        get() = _navigateToUserDetails
    private val _errorToast = MutableLiveData<Boolean>()
    val errotoast: LiveData<Boolean>
        get() = _errorToast
    private val _errorToastInvalidDate = MutableLiveData<Boolean>()
    val errorToastInvalidDate: LiveData<Boolean>
        get() = _errorToastInvalidDate

    fun login(username: String, password: String) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            _errorToast.value = true
            //Share Preferences
            myPreferen = MyPreference(this.context)
            myPreferen.PREFERENCE_MY_NAME = username
            myPreferen.getLoginName()

        } else {
            viewModelScope.launch {
                when (val users = getUsersCase.invoke()) {
                    is Result.Success -> {
                        eventMutableLiveData.value = UserEvent.SetUsers(users.value)
                        var user = users.value.map { user -> user.userName!! }
                        var pass = users.value.map { pass -> pass.password!! }
                        if (user.contains(username) && pass.contains(password)) {
                            _navigateToUserDetails.value = true

                        } else {
                            _errorToastInvalidDate.value = true
                        }
                    }
                    is Result.Error -> {
                        eventMutableLiveData.value = UserEvent.ShowError
                    }
                }
            }
        }
    }

    fun doneNavigatingUserDetails() {
        _navigateToUserDetails.value = false
    }

    fun doneToast() {
        _errorToast.value = false
    }

    fun donetoastInvalidDate() {
        _errorToastInvalidDate.value = false
    }

    class MyPreference(context: Context?) {

        var PREFERENCE_NAME = "Name"
        var PREFERENCE_MY_NAME = ""
        val preference: SharedPreferences? =
            context?.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        fun getLoginName(): String {
            return preference?.getString(PREFERENCE_NAME, PREFERENCE_MY_NAME).toString()
        }

        fun setLoginName(name: String) {
            val editor = preference?.edit()
            editor?.putString(PREFERENCE_NAME, name)
            editor?.apply()
        }
    }
}
