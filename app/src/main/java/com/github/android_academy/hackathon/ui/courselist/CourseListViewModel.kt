package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.Screens
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

    private val _mutablecourses = MutableLiveData<List<Course>>()//TODO добавить метод поолучения курсов

    val courses:LiveData<List<Course>> get() = _mutablecourses

    fun onCourseAction(course: Course){
        //TODO получить курс
    }

    fun addCourseAction(){
        router.navigateTo(Screens.AddCourseFragment(),false )
        //TODO добавление курса. На то что ментор уже проверили
    }

    fun subscribeAction(course: Course){

        //TODO запросить на беке список курсов, обновить локальынй
    }

    fun isMentor() = user.value?.isMentor == true
}