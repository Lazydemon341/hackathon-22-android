package com.github.android_academy.hackathon.ui.addlecture

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.AddLectureFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.addlecture.DaggerAddLectureViewModelComponent
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.ViewState

class AddLectureFragment : BaseFragment(R.layout.add_lecture_fragment, true) {
    private val binding by viewBinding(AddLectureFragmentBinding::bind)


    private val viewModel: AddLectureViewModel by viewModels(
        factoryProducer = { AddLectureViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)


        binding.addCourseFab.setOnClickListener {
            val lecture = Lecture(
                title = binding.addLectureFragmentTitle.editText?.text.toString(),
                youtubeUrl = binding.addLectureFragmentYoutubeUrl.editText?.text.toString(),
                githubRepoUrl = binding.addLectureFragmentGithub.editText?.text.toString(),
                telegramChannel = binding.addLectureFragmentTelegramChannel.editText?.text.toString(),
                additionalMaterials = emptyList(), //TODO может чем-то заменить
                imgUrl = binding.addLectureFragmentImgUrl.editText?.text.toString(),
                tags = binding.addLectureFragmentTags.editText?.text.toString().split(" "),
                courseId = arguments?.getLong(COURSE_ID)!! //TODO получить из Bundle
            )
            viewModel.addLecture(lecture)
        }

        viewModel.singleLiveEvent.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Error -> {
                    //TODO
                }
                ViewState.Loading -> {
                    //TODO
                }
                is ViewState.Success -> viewModel.exitFragment()
            }
        }
    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }

    companion object {
        private const val COURSE_ID = "course_id"

        @JvmStatic
        fun newInstance(courseId: Long): AddLectureFragment {
            val fragment = AddLectureFragment()
            val bundle = Bundle()
            bundle.putLong(COURSE_ID, courseId)
            fragment.arguments = bundle
            return fragment
        }
    }
}

private class AddLectureViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerAddLectureViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}