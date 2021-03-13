package com.github.android_academy.hackathon.ui.addlecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AddLectureViewModel @Inject constructor(
    private val router: Router
):ViewModel() {
    fun addLecture(lecture: Lecture) {
        viewModelScope.launch {
        }
    }

    fun exitFragment(){
        router.exit()
    }
}