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
    private val mutableUser = MutableLiveData<User>(authRepository.loadUser())

    val user: LiveData<User> get() = mutableUser

    private val mutableCourses =
        MutableLiveData<ViewState<List<Course>, String?>>()

    val courses: LiveData<ViewState<List<Course>, String?>> get() = mutableCourses

    fun onCourseAction(course: Course) {
        router.navigateTo(Screens.lecturesListFragment(course))
    }

    fun addCourseAction() {
        router.navigateTo(Screens.addCourseFragment())
    }



    fun subscribeAction(course: Course) {
        viewModelScope.launch {
            val updateCourse = Course(
                id = course.id,
                title = course.title,
                shortDescription = course.shortDescription,
                fullDescription = course.fullDescription,
                imgUrl = course.imgUrl,
                tags = course.tags,
                isSubscribed = !course.isSubscribed
            )
            courseRepository.updateCourse(updateCourse)
        }
    }

    fun showFavoriteCourses() {
        viewModelScope.launch {
            mutableCourses.value = ViewState.loading()
            when (val coursesResult = courseRepository.getFavouriteCourses(user.value?.username ?: "")) {
                is OperationResult.Success -> mutableCourses.value =
                    ViewState.success(coursesResult.data ?: emptyList())
                is OperationResult.Error -> mutableCourses.value =
                    ViewState.error(coursesResult.data)
            }
        }
    }

    fun showAllCourses() {
        viewModelScope.launch {
            mutableCourses.value = ViewState.loading()
            when (val coursesResult = courseRepository.getAllCourses()) {
                is OperationResult.Success -> mutableCourses.value =
                    ViewState.success(coursesResult.data ?: emptyList())
                is OperationResult.Error -> mutableCourses.value =
                    ViewState.error(coursesResult.data)
            }
        }
    }

    fun exitFragment(){
        router.exit()
    }

    fun isMentor() = user.value?.isMentor == true
}