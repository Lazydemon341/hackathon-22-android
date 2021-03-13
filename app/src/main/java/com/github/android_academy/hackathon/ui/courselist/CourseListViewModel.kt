package com.github.android_academy.hackathon.ui.courselist

import android.util.Log
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
        viewModelScope.launch {
            courseRepository.updateCourse(course)

            val coursesResult = courseRepository.getAllCourses()
            when(coursesResult){
                is OperationResult.Success -> _mutablecourses.value = coursesResult.data?: emptyList()
                is OperationResult.Error -> Log.e("Backend error", coursesResult.data.toString())
            }
        }
    }

    fun showFavoriteCourses(){
        viewModelScope.launch {
            val coursesResult = courseRepository.getFavouriteCourses(user.value?.username ?: "-1") //TODO посмотреть что тправлять на бэк при Null username
            when(coursesResult){
                is OperationResult.Success -> _mutablecourses.value = coursesResult.data?: emptyList()
                is OperationResult.Error -> Log.e("Backend error", coursesResult.data.toString())
            }
        }
    }

    fun showAllCourses(){
        viewModelScope.launch {
            val coursesResult = courseRepository.getAllCourses()
            when(coursesResult){
                is OperationResult.Success -> _mutablecourses.value = coursesResult.data?: emptyList()
                is OperationResult.Error -> Log.e("Backend error", coursesResult.data.toString())
            }
        }
    }

    fun isMentor() = user.value?.isMentor == true
}