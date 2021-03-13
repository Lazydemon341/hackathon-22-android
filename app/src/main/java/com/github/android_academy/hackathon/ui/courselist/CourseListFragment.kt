package com.github.android_academy.hackathon.ui.courselist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.CourseListFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.courselist.DaggerCourseListViewModelComponent
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.loginfragment.LoginFragment
import com.github.android_academy.hackathon.ui.registrationfragment.RegistrationFragment

class CourseListFragment : BaseFragment(R.layout.course_list_fragment){
    private val binding by viewBinding(CourseListFragmentBinding::bind)


    private val viewModel: CourseListViewModel by viewModels(
            factoryProducer = { CourseListViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        //TODO observe

        //recycler
        val coursesAdapter = CoursesAdapter {
            viewModel.onCourseAction(it)
        }
        binding.courseListFragmentRecycler.adapter = coursesAdapter

        //fab
        binding.courseFragmentFab.setOnClickListener {
            viewModel.addCourseAction() }
    }


    companion object {
        private const val KEY_USER = "user"
        @JvmStatic
        fun newInstance() = LoginFragment()
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