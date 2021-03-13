package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseListViewModel @Inject constructor(
    private val router : Router,
    private val courseRepository : CourseRepository,
    private val authRepository: AuthRepository
) : ViewModel(){
    //TODO: implement view model
    private val _mutableuser = MutableLiveData<User>(authRepository.loadUser())

    val user: LiveData<User> get() = _mutableuser

    fun onCourseAction(course: Course){
        //TODO получить курс
    }

    fun addCourseAction(){
        //TODO добавление курса. На то что ментор уже проверили
    }

    fun addToFavoriteAction(course: Course){
        //TODO обновить юзера, обновить иконку
    }

    fun isMentor() = user.value?.isMentor == true
}