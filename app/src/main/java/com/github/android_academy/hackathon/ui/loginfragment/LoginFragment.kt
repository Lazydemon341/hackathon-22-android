package com.github.android_academy.hackathon.ui.loginfragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LoginFragmentBinding
import com.github.android_academy.hackathon.di.login.DaggerLoginViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment

class SampleFragment : BaseFragment(R.layout.login_fragment) {
    private val binding by viewBinding(LoginFragmentBinding::bind)

    private val viewModel: LoginViewModel by viewModels(
        factoryProducer = { LoginViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
//        viewModel.sample.observe(viewLifecycleOwner) { sample ->
//            binding.sampleField.text = sample.text
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SampleFragment()
    }
}

private class LoginViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DaggerLoginViewModelComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .provideViewModel() as T
    }
}