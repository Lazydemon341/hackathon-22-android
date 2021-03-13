package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.android_academy.hackathon.ui.ViewState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseListViewModel @Inject constructor(
    private val router: Router,
    private val courseRepository: CourseRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    //TODO: implement view model
    private val _mutableuser = MutableLiveData<User>(authRepository.loadUser())

    val user: LiveData<User> get() = _mutableuser

    private val _mutablecourses =
        MutableLiveData<ViewState<List<Course>, String?>>()//TODO добавить метод поолучения курсов

    val courses: LiveData<ViewState<List<Course>, String?>> get() = _mutablecourses

    fun onCourseAction(course: Course) {
        router.navigateTo(Screens.lecturesListFragment())
    }

    fun addCourseAction() {
        router.navigateTo(Screens.addCourseFragment())
    }



    fun subscribeAction(course: Course) {
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
    }

    fun showFavoriteCourses() {
        viewModelScope.launch {
            _mutablecourses.value = ViewState.loading()
            when (val coursesResult = courseRepository.getFavouriteCourses(user.value?.username ?: "")) {
                is OperationResult.Success -> _mutablecourses.value =
                    ViewState.success(coursesResult.data ?: emptyList())
                is OperationResult.Error -> _mutablecourses.value =
                    ViewState.error(coursesResult.data)
            }
        }
    }

    fun showAllCourses() {
        viewModelScope.launch {
            _mutablecourses.value = ViewState.loading()
            when (val coursesResult = courseRepository.getAllCourses()) {
                is OperationResult.Success -> _mutablecourses.value =
                    ViewState.success(coursesResult.data ?: emptyList())
                is OperationResult.Error -> _mutablecourses.value =
                    ViewState.error(coursesResult.data)
            }
        }
    }

    fun exitFragment(){
        router.exit()
    }

    fun isMentor() = user.value?.isMentor == true
}