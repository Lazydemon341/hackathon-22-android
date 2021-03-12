package com.github.android_academy.hackathon

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.android_academy.hackathon.ui.sample.SampleFragment
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun SampleScreen(): FragmentScreen =
        FragmentScreen(
            fragmentCreator = FragmentCreator(SampleFragment.newInstance())
        )

    class FragmentCreator(private val fragment: Fragment) : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment =
            fragment
    }
}
