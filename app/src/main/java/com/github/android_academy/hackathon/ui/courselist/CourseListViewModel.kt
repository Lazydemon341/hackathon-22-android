package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CourseListViewModel @Inject constructor(
    private val router : Router,
    private val repository : CourseRepository
) : ViewModel(){
    //TODO: implement view model
}