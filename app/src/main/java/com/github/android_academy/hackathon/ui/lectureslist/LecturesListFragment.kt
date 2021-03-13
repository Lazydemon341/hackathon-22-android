package com.github.android_academy.hackathon.ui.lectureslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LecturesListFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.lectureslist.DaggerLecturesListViewModelComponent
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.ViewState
import com.github.android_academy.hackathon.ui.registration.RegistrationFragment


class LecturesListFragment : BaseFragment(R.layout.lectures_list_fragment) {
    private val binding by viewBinding(LecturesListFragmentBinding::bind)

    private val lecturesAdapter =
        LecturesListAdapter({ viewModel.onLectureAction(it) }) //TODO добавить нормальный callback при нажатии на лекцию

    private val viewModel: LecturesListViewModel by viewModels(
        factoryProducer = { LecturesListViewModelFactory() }
    )


    override fun initViews(view: View) {
        super.initViews(view)
        //TODO observe

        //recycler
        binding.lecturesListFragmentRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.lecturesListFragmentRecycler.adapter = lecturesAdapter

        //fab
        if (!viewModel.isMentor()) binding.lecturesFragmentFab.hide() //спрятать если не ментор
        binding.lecturesFragmentFab.setOnClickListener {
            viewModel.addLectureAction()
        }


        //observe
        viewModel.lectures.observe(viewLifecycleOwner, this::updateAdapter)


        viewModel.updateLectures(arguments?.getLong(COURSE_ID_KEY) ?: 0)
    }

    private fun updateAdapter(lectures: ViewState<List<Lecture>, String?>) {
        when (lectures) {
            is ViewState.Success -> {
                //TODO Загрузка
                lecturesAdapter.submitList(lectures.result)
            }
            //ViewState.Loading ->  //TODO Загрузка
            is ViewState.Error -> {
                //TODO Загрузка
                showError()
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Couldn't load дусегкуы...", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }

    companion object {
        private const val COURSE_ID_KEY = "course_id"

        @JvmStatic
        fun newInstance(course: Course) : LecturesListFragment{
            val fragment = LecturesListFragment()
            val bundle = Bundle()
            bundle.putLong(COURSE_ID_KEY, course.id!!)
            fragment.arguments = bundle
            return fragment
        }
    }
}

private class LecturesListViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerLecturesListViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}