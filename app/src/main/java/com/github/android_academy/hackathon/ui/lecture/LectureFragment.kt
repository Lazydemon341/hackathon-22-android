package com.github.android_academy.hackathon.ui.lecture

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LectionFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.lecture.DaggerLectureViewModelComponent
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.ui.BaseFragment


class LectureFragment :BaseFragment(R.layout.lection_fragment, true) {
    private val binding by viewBinding(LectionFragmentBinding::bind)


    private val viewModel: LectureViewModel by viewModels(
        factoryProducer = { LectureViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)

        //TODO show information in all views
        val lecture = arguments?.getParcelable<Lecture>(LECTURE_KEY)
        Glide
            .with(requireContext())
            .load(lecture?.imgUrl)
            .centerCrop()
            //.placeholder(R.)
            .into(binding.lectureImgurl)
        binding.linkGithub.text


        //fab
        binding.linkGithub.text = lecture?.githubRepoUrl
        binding.linkYoutube.text = lecture?.youtubeUrl
        binding.ratingBar.numStars = 5
    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }

    companion object {
        private const val LECTURE_KEY = "lecture_key"
        @JvmStatic
        fun newInstance(lecture:Lecture): LectureFragment{
            val fragment = LectureFragment()
            val bundle = Bundle()
            bundle.putParcelable(LECTURE_KEY,lecture)
            fragment.arguments = bundle
            return fragment
        }
    }
}

private class LectureViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerLectureViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}