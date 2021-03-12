package com.github.android_academy.hackathon.ui.registrationfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.ui.ViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private lateinit var mutableRegistrationResult: MutableLiveData<ViewState<Boolean>>

    val registrationResult: LiveData<ViewState<Boolean>> = mutableRegistrationResult

    fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ) {
        viewModelScope.launch {
            mutableRegistrationResult.postValue(ViewState.loading())
            mutableRegistrationResult.postValue(
                authRepository.register(
                    username,
                    password,
                    name,
                    isMentor
                )
            )
        }
    }
}