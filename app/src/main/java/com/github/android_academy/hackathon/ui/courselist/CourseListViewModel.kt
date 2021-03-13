package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.domain.models.Course
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CourseListViewModel @Inject constructor(
    private val router : Router,

) : ViewModel(){

    fun onCourseAction(course: Course){
        //TODO обработка нажатия на курс
    }

    fun addCourseAction(){

    }
    //TODO: implement view model
}