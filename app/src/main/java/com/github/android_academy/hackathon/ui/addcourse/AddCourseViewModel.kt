package com.github.android_academy.hackathon.ui.addcourse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val router: Router
) : ViewModel() {

    fun addCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
        router.navigateTo(Screens.CourseListFragment())
    }
}