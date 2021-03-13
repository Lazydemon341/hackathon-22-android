package com.github.android_academy.hackathon.ui.lectureslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.android_academy.hackathon.ui.ViewState
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LecturesListViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _mutableuser = MutableLiveData<User>(authRepository.loadUser())

    val user: LiveData<User> get() = _mutableuser

    private val _mutableLectures =
        MutableLiveData<ViewState<List<Lecture>, String?>>()//TODO добавить метод поолучения курсов

    val lectures: LiveData<ViewState<List<Lecture>, String?>> get() = _mutableLectures

    fun onLectureAction(lecture: Lecture){

    }

    fun addLectureAction(){
        //TODO Экран с добавлением лекции
        router.navigateTo(Screens.addLectureFragment())
    }

    fun exitFragment(){
        router.exit()
    }

    fun isMentor() = user.value?.isMentor == true
}