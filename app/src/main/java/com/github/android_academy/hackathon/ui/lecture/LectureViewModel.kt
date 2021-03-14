package com.github.android_academy.hackathon.ui.lecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.LecturesRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class LectureViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthRepository,
    private val lecturesRepository: LecturesRepository
) : ViewModel() {

    private val mutableLectureResult: MutableLiveData<Lecture?> =
        MutableLiveData()

    val lectureResult: LiveData<Lecture?> get() = mutableLectureResult

    fun getLecture(id: Long?) {
        if (id != null) {
            viewModelScope.launch {
                mutableLectureResult.value = lecturesRepository.getLecture(id)
            }
        }
    }

    fun exitFragment() {
        router.exit()
    }

    fun isMentor() = authRepository.loadUser()?.isMentor == true
}