package com.github.android_academy.hackathon.ui.addlecture

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.ui.ViewState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class AddLectureViewModel @Inject constructor(
    private val router: Router
):ViewModel() {
    fun addLecture(lecture: Lecture) {
        viewModelScope.launch {
            //TODO добавть здесь аналонично addCourse из VM addCourse
        }
    }

    fun exitFragment(){
        router.exit()
    }
}
