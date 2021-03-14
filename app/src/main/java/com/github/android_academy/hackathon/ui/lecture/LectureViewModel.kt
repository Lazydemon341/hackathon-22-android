package com.github.android_academy.hackathon.ui.lecture

import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LectureViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthRepository
) :ViewModel(){

    fun exitFragment(){
        router.exit()
    }

    fun isMentor() = authRepository.loadUser()?.isMentor == true
}