package com.github.android_academy.hackathon.ui.registrationfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.ui.ViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val mutableRegistrationResult: MutableLiveData<ViewState<Unit, String?>> =
        MutableLiveData()

    val registrationResult: LiveData<ViewState<Unit, String?>> get() = mutableRegistrationResult

    fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ) {
        viewModelScope.launch {
            mutableRegistrationResult.value = ViewState.loading()

            val result = authRepository.register(
                    username,
                    password,
                    name,
                    isMentor
                )
            mutableRegistrationResult.value = when(result){
                is OperationResult.Error -> ViewState.error(result.data)
                is OperationResult.Success -> ViewState.success(result.data)
            }
        }
    }
}