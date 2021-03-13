package com.github.android_academy.hackathon.ui.addcourse

import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val router : Router
): ViewModel() {

    fun addCourse(course : Course){
        TODO("Not yet implemented")
    }
}