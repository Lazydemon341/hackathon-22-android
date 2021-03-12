package com.github.android_academy.hackathon

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.android_academy.hackathon.ui.loginfragment.LoginFragment
import com.github.android_academy.hackathon.ui.registrationfragment.RegistrationFragment
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun LoginFragment(): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(LoginFragment.newInstance())
        )

    fun RegistrationFragment(): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(RegistrationFragment.newInstance())
        )

    fun RegistrationFragment(login:String, password : String): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(RegistrationFragment.newInstance(login, password))
        )

    class FragmentCreator(private val fragment: Fragment) : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment =
            fragment
    }
}
