package com.example.loginappregistrer.presentation.viewmodel

import com.example.loginappregistrer.domain.model.User

sealed class UserEvent {
    data class SetUsers(val user: List<User>) : UserEvent()
    object ShowLoading : UserEvent()
    object ShowError : UserEvent()
}


