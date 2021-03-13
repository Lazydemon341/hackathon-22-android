package com.github.android_academy.hackathon.ui.addlecture

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.AddCourseFragmentBinding
import com.github.android_academy.hackathon.databinding.AddLectureFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.addcourse.DaggerAddCourseViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment

class AddLectureFragment :BaseFragment(R.layout.add_lecture_fragment){
    private val binding by viewBinding(AddLectureFragmentBinding::bind)


    private val viewModel: AddLectureViewModel by viewModels(
        factoryProducer = { AddLectureViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)

        binding.addCourseFab.setOnClickListener {
            //TODO создать лекцйию и вызвать addLecture
        }
    }
    override fun onBackPressed() {
        viewModel.exitFragment()
    }
    companion object {
        @JvmStatic
        fun newInstance() = AddLectureFragment()
    }
}

private class AddLectureViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerAddCourseViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}