package com.github.android_academy.hackathon.ui.lecture

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LectionFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.lecture.DaggerLectureViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.addcourse.AddCourseFragment


class LectureFragment :BaseFragment(R.layout.lection_fragment) {
    private val binding by viewBinding(LectionFragmentBinding::bind)


    private val viewModel: LectureViewModel by viewModels(
        factoryProducer = { LectureViewModelFactory() }
    )

    override fun onBackPressed() {
        viewModel.exitFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LectureFragment()
    }
}

private class LectureViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerLectureViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}