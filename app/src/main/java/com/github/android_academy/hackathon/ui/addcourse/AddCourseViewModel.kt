package com.github.android_academy.hackathon.ui.addcourse

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val router: Router
) : ViewModel() {

    fun addCourse(course: Course) {
        viewModelScope.launch {
            val updateResult = courseRepository.updateCourse(course)
            Timber.d(updateResult.toString())
            exitFragment()
        }
    }

    fun exitFragment(){
        router.exit()
    }
}