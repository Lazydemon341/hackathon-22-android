package com.github.android_academy.hackathon.ui.datepicker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.DatePickerFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.datepicker.DaggerDatePickerViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment
import java.util.*

class DatePickerFragment() : BaseFragment(R.layout.date_picker_fragment,
    false, "Pick date"
) {
    private val binding by viewBinding(DatePickerFragmentBinding::bind)


    private val viewModel: DatePickerViewModel by viewModels(
        factoryProducer = { DatePickerViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)

        binding.dialogDatePicker.updateDate(
            arguments?.get(YEAR) as Int, arguments?.get(MONTH) as Int, arguments?.get(DAY) as Int )
    }

    companion object {
        private const val DAY = "DAY"
        private const val MONTH = "MONTH"
        private const val YEAR = "YEAR"
        @JvmStatic
        fun newInstance(calendar: Calendar):  DatePickerFragment {
            val fragment = DatePickerFragment()
            val bundle = Bundle()
            bundle.putInt(DAY, calendar.get(Calendar.DAY_OF_MONTH))
            bundle.putInt(MONTH, calendar.get(Calendar.MONTH))
            bundle.putInt(YEAR, calendar.get(Calendar.YEAR))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }
}

private class DatePickerViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerDatePickerViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}