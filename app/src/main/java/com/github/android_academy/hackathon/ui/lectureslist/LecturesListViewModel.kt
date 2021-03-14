package com.github.android_academy.hackathon.ui.lectureslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.android_academy.hackathon.ui.ViewState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class LecturesListViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthRepository,
    private val courseRepository: CourseRepository
): ViewModel() {
    private val _mutableuser = MutableLiveData<User>(authRepository.loadUser())

    val user: LiveData<User> get() = _mutableuser

    private val _mutableLectures =
        MutableLiveData<ViewState<List<Lecture>, String?>>()//TODO Получение лекций

    val lectures: LiveData<ViewState<List<Lecture>, String?>> get() = _mutableLectures

    fun updateLectures(courseId:Long){
        viewModelScope.launch {
            _mutableLectures.value = ViewState.loading()
            when (val coursesResult = courseRepository.getAllLectures(courseId)) {
                is OperationResult.Success -> _mutableLectures.value =
                    ViewState.success(coursesResult.data ?: emptyList())
                is OperationResult.Error -> _mutableLectures.value =
                    ViewState.error(coursesResult.data)
            }
        }
    }

    fun onLectureAction(lecture: Lecture){
        router.navigateTo(Screens.lectureFragment(lecture.id!!))
    }

    fun addLectureAction(courseId: Long){
        router.navigateTo(Screens.addLectureFragment(courseId))
    }

    fun exitFragment(){
        router.exit()
    }

    fun isMentor() = user.value?.isMentor == true
}