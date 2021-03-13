package com.github.android_academy.hackathon.ui.courselist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.CourseListFragmentBinding
import com.github.android_academy.hackathon.databinding.LoginFragmentBinding
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.di.viewmodels.courselist.DaggerCourseListViewModelComponent


class CourseListFragment : BaseFragment(R.layout.course_list_fragment) {

    private val binding by viewBinding(CourseListFragmentBinding::bind)


    private val viewModel: CourseListViewModel by viewModels(
        factoryProducer = { CourseListViewModelFactory() }
    )

    companion object {
        @JvmStatic
        fun newInstance() = CourseListFragment()
    }

}

private class CourseListViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerCourseListViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}

