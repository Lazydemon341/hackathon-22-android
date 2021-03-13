package com.github.android_academy.hackathon

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.ui.courselist.CourseListFragment
import com.github.android_academy.hackathon.ui.login.LoginFragment
import com.github.android_academy.hackathon.ui.registration.RegistrationFragment
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.android_academy.hackathon.ui.addcourse.AddCourseFragment
import com.github.android_academy.hackathon.ui.addlecture.AddLectureFragment
import com.github.android_academy.hackathon.ui.lecture.LectureFragment
import com.github.android_academy.hackathon.ui.lectureslist.LecturesListFragment

object Screens {
    fun loginFragment(): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(LoginFragment.newInstance())
        )

    fun registrationFragment(): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(RegistrationFragment.newInstance())
        )

    fun registrationFragment(login:String, password : String): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(RegistrationFragment.newInstance(login, password))
        )

    fun courseListFragment(): FragmentScreen =
            FragmentScreen(
                    fragmentCreator = FragmentCreator(CourseListFragment.newInstance())
            )

    fun lecturesListFragment(course : Course) : FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(LecturesListFragment.newInstance(course))
        )

    fun addLectureFragment() : FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(AddLectureFragment.newInstance())
        )

    fun lectureFragment() : FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(LectureFragment.newInstance())
        )


    fun addCourseFragment() : FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(AddCourseFragment.newInstance())
        )

    class FragmentCreator(private val fragment: Fragment) : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment =
            fragment
    }
}
