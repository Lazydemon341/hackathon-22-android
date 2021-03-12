package com.github.android_academy.hackathon.ui.loginfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.ui.ViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val mutableLoginResult: MutableLiveData<ViewState<Unit, String>> = MutableLiveData()

    val loginResult: LiveData<ViewState<Unit, String>> get() = mutableLoginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            mutableLoginResult.value = ViewState.loading()

            val result = authRepository.login(username, password)
            mutableLoginResult.value = when (result) {
                is OperationResult.Error -> ViewState.error(result.data)
                is OperationResult.Success -> ViewState.success(result.data)
            }
        }
    }
}