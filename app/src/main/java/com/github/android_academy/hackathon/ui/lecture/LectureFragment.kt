package com.github.android_academy.hackathon.ui.lecture

import android.content.Intent
import android.net.Uri
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


class LectureFragment :BaseFragment(R.layout.lection_fragment) {
    private val binding by viewBinding(LectionFragmentBinding::bind)


    private val viewModel: LectureViewModel by viewModels(
        factoryProducer = { LectureViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)

        val lecture = arguments?.getParcelable<Lecture>(LECTURE_KEY)

        //Load main picture
        Glide
            .with(requireContext())
            .load(lecture?.imgUrl)
            .centerCrop()
            .placeholder(R.drawable.academy_logo)
            .into(binding.lectureImgurl)

        binding.ratingBar.rating = 5F

        //youtube
        binding.lectionFragmentYoutubeUrl.setText(lecture?.youtubeUrl)
        binding.lectionFragmentYoutubeButton.setOnClickListener {
            val youtubeIntent: Intent = Intent(Intent.ACTION_VIEW)
            youtubeIntent.setData(Uri.parse(lecture?.youtubeUrl))
            startActivity(Intent.createChooser(youtubeIntent, "Choose youtube video player"))
        }

        //github
        binding.lectionFragmentTelegramUrl.setText(lecture?.telegramChannel)
        binding.lectionFragmentTelegramButton.setOnClickListener {
            val telegramIntent: Intent = Intent(Intent.ACTION_VIEW,Uri.parse(lecture?.telegramChannel))
            startActivity(telegramIntent)
        }

        //telegram
        binding.lectionFragmentGithubUrl.setText(lecture?.githubRepoUrl)
        binding.lectionFragmentGithubButton.setOnClickListener {
            val githubIntent: Intent = Intent(Intent.ACTION_VIEW)
            githubIntent.setData(Uri.parse(lecture?.githubRepoUrl))
            startActivity(Intent.createChooser(githubIntent, "Choose telegram"))
        }

    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }

    companion object {
        private const val LECTURE_KEY = "lecture_key"
        @JvmStatic
        fun newInstance(lecture: Lecture): LectureFragment{
            val fragment = LectureFragment()
            val bundle = Bundle()
            bundle.putParcelable(LECTURE_KEY, lecture)
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