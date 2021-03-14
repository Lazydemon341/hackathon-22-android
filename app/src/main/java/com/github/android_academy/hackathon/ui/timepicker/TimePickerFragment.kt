package com.github.android_academy.hackathon.ui.timepicker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.DatePickerFragmentBinding
import com.github.android_academy.hackathon.databinding.TimePickerFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.datepicker.DaggerDatePickerViewModelComponent
import com.github.android_academy.hackathon.di.viewmodels.timepicker.DaggerTimePickerViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment
import java.util.*

class TimePickerFragment() : BaseFragment(R.layout.time_picker_fragment,
    false
) {
    private val binding by viewBinding(TimePickerFragmentBinding::bind)


    private val viewModel: TimePickerViewModel by viewModels(
        factoryProducer = { TimePickerViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)

        binding.dialogTimePicker.currentHour = arguments?.getInt(HOURS) ?: 0
        binding.dialogTimePicker.currentHour = arguments?.getInt(MINUTE) ?: 0

    }

    companion object {
        private const val HOURS = "hours"
        private const val MINUTE = "minute"
        @JvmStatic
        fun newInstance(calendar: Calendar):  TimePickerFragment {
            val fragment = TimePickerFragment()
            val bundle = Bundle()
            bundle.putInt(HOURS, calendar.get(Calendar.HOUR_OF_DAY))
            bundle.putInt(MINUTE, calendar.get(Calendar.MINUTE))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }
}

private class TimePickerViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerTimePickerViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}