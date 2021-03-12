package com.github.android_academy.hackathon.ui.loginfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.ui.ViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private lateinit var mutableLoginResult: MutableLiveData<ViewState<Boolean>>

    val loginResult: LiveData<ViewState<Boolean>> = mutableLoginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            mutableLoginResult.postValue(ViewState.loading())
            mutableLoginResult.postValue(authRepository.login(username, password))
        }
    }
}