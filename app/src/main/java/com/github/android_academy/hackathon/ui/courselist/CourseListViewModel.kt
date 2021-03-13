package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CourseListViewModel @Inject constructor(
    private val router : Router,
    private val courseRepository : CourseRepository,
    private val authRepository: AuthRepository
) : ViewModel(){
    //TODO: implement view model
}