package com.github.android_academy.hackathon.ui.addcourse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.ui.BaseFragment

class AddCourseFragment : BaseFragment(R.layout.add_course_fragment) {

    private lateinit var viewModel: AddCourseViewModel

    companion object {
        fun newInstance() = AddCourseFragment()
    }
}