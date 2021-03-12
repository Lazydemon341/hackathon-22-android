package com.github.android_academy.hackathon.ui.registrationfragment

import androidx.lifecycle.ViewModelProvider
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.RegistrationFragmentBinding
import com.github.android_academy.hackathon.di.registration.DaggerRegistrationViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment

class RegistrationFragment : BaseFragment(R.layout.registration_fragment) {
    private val binding by viewBinding(RegistrationFragmentBinding::bind)

    private val viewModel: RegistrationViewModel by viewModels(
        factoryProducer = { RegistrationViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
//        viewModel.sample.observe(viewLifecycleOwner) { sample ->
//            binding.sampleField.text = sample.text
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

}

private class RegistrationViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerRegistrationViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}