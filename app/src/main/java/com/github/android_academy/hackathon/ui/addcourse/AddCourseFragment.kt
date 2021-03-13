package com.github.android_academy.hackathon.ui.addcourse

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.AddCourseFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.addcourse.DaggerAddCourseViewModelComponent
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.ui.BaseFragment

class AddCourseFragment : BaseFragment(R.layout.add_course_fragment) {
    private val binding by viewBinding(AddCourseFragmentBinding::bind)


    private val viewModel: AddCourseViewModel by viewModels(
        factoryProducer = { AddCourseViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        //TODO observe

        binding.doneAddCourseFab.setOnClickListener {
            val course = Course(
                title = binding.addCourseTitle.editText?.text.toString(),
                shortDescription = binding.addCourseShortDescription.editText?.text.toString(),
                fullDescription = binding.addCourseFullDescription.editText?.text.toString(),
                imgUrl = binding.addCourseImageUrl.editText?.text.toString(),
                tags = binding.addCourseTags.editText?.text.toString().split(" ")
            )
            viewModel.addCourse(course)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = AddCourseFragment()
    }
}

private class AddCourseViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerAddCourseViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}