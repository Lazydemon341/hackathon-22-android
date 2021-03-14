package com.github.android_academy.hackathon

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.android_academy.hackathon.ui.addcourse.AddCourseFragment
import com.github.android_academy.hackathon.ui.addlecture.AddLectureFragment
import com.github.android_academy.hackathon.ui.courselist.CourseListFragment
import com.github.android_academy.hackathon.ui.datepicker.DatePickerFragment
import com.github.android_academy.hackathon.ui.lecture.LectureFragment
import com.github.android_academy.hackathon.ui.lectureslist.LecturesListFragment
import com.github.android_academy.hackathon.ui.login.LoginFragment
import com.github.android_academy.hackathon.ui.registration.RegistrationFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import java.util.*

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

    fun lecturesListFragment(id: Long): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(LecturesListFragment.newInstance(id))
        )

    fun datePickerFragment(calendar: Calendar) : FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(DatePickerFragment.newInstance(calendar))
        )

    fun addLectureFragment(courseId:Long) : FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(AddLectureFragment.newInstance(courseId))
        )

    fun lectureFragment(lectureId: Long?): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(LectureFragment.newInstance(lectureId))
        )


    fun addCourseFragment(): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(AddCourseFragment.newInstance())
        )

    fun youtubeScreen(uri: Uri) =
        ActivityScreen(intentCreator = IntentCreator(intent = Intent(Intent.ACTION_VIEW, uri)))

    class FragmentCreator(private val fragment: Fragment) : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment =
            fragment
    }

    class IntentCreator(private val intent: Intent) : Creator<Context, Intent> {
        override fun create(argument: Context): Intent =
            intent
    }
}
