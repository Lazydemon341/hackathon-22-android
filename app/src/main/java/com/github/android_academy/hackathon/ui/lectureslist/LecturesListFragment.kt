package com.github.android_academy.hackathon.ui.lectureslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LecturesListFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.lectureslist.DaggerLecturesListViewModelComponent
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.courselist.CoursesAdapter


class LecturesListFragment : BaseFragment(R.layout.lectures_list_fragment){
    private val binding by viewBinding(LecturesListFragmentBinding::bind)

    private val lecturesAdapter = LecturesListAdapter(::callback) //TODO добавить нормальный callback при нажатии на лекцию

    private val viewModel: LecturesListViewModel by viewModels(
        factoryProducer = { LecturesListViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        //TODO observe

        //recycler
        binding.lecturesListFragmentRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.lecturesListFragmentRecycler.adapter = lecturesAdapter
    }

    private fun callback(lecture: Lecture){}



    companion object {
        @JvmStatic
        fun newInstance() = LecturesListFragment()
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