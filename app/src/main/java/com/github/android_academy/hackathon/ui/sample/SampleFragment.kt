package com.github.android_academy.hackathon.ui.sample

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.SampleScreenBinding
import com.github.android_academy.hackathon.di.sample.DaggerSampleViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment

class SampleFragment : BaseFragment(R.layout.sample_screen) {
    private val binding by viewBinding(SampleScreenBinding::bind)

    private val viewModel: SampleViewModel by viewModels(
        factoryProducer = { SampleViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        viewModel.sample.observe(viewLifecycleOwner) { sample ->
            binding.sampleField.text = sample.text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SampleFragment()
    }
}

private class SampleViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerSampleViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}