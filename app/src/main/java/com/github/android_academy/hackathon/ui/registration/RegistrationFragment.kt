package com.github.android_academy.hackathon.ui.registration

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.RegistrationFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.registration.DaggerRegistrationViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.ViewState

class RegistrationFragment : BaseFragment(R.layout.registration_fragment, false) {
    private val binding by viewBinding(RegistrationFragmentBinding::bind)

    private val viewModel: RegistrationViewModel by viewModels(
        factoryProducer = { RegistrationViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        viewModel.registrationResult.observe(viewLifecycleOwner, { checkRegistrationResult(it) })

        binding.registrationFragmentLogin.editText?.setText(arguments?.getString(KEY_LOGIN))
        binding.registrationFragmentPassword.editText?.setText(arguments?.getString(KEY_PASSWORD))

        binding.registrationFragmentSignUpButton.setOnClickListener {
            if (validateFields()) {
                viewModel.register(
                    username = binding.registrationFragmentLogin.editText?.text.toString(),
                    password = binding.registrationFragmentPassword.editText?.text.toString(),
                    name = binding.registrationFragmentName.editText?.text.toString(),
                    isMentor = binding.registrationFragmentIsmentorCheckbox.isChecked
                )
            }
        }
    }

    private fun validateFields(): Boolean {
        if (binding.registrationFragmentName.editText?.text.toString().isNullOrBlank()) {
            binding.registrationFragmentName.error = "Enter a valid name!"
            return false
        }
        if (binding.registrationFragmentLogin.editText?.text.toString().isNullOrBlank()) {
            binding.registrationFragmentLogin.error = "Enter a valid name!"
            return false
        }
        if (binding.registrationFragmentPassword.editText?.text.toString().isNullOrBlank()) {
            binding.registrationFragmentPassword.error = "Enter a valid name!"
            return false
        }
        return comparePasswords()
    }

    private fun comparePasswords(): Boolean {
        if (binding.registrationFragmentPassword.editText?.text?.toString() ==
            binding.registrationFragmentPassword2.editText?.text?.toString()
        ) {
            return true
        } else {
            binding.registrationFragmentPassword2.error = getString(R.string.error_wrong_password)
            return false
        }
    }

    private fun checkRegistrationResult(it: ViewState<Unit, String?>) {
        when (it) {
            is ViewState.Loading -> {
                binding.registrationProgressBar.isVisible = true
            }
            is ViewState.Error -> {
                binding.registrationProgressBar.isVisible = false
                wrongPassword(it.result ?: "Unknown login error")
            }
            is ViewState.Success -> {
                binding.registrationProgressBar.isVisible = false
                viewModel.launchCourseList()
            }
        }
    }

    override fun onBackPressed() {
        viewModel.goToPreviousFragment()
    }

    private fun wrongPassword(message: String) {
        binding.registrationFragmentLogin.error = message
    }

    companion object {
        private const val KEY_LOGIN = "login"
        private const val KEY_PASSWORD = "password"

        @JvmStatic
        fun newInstance(login: String, password: String): RegistrationFragment {
            val fragment = RegistrationFragment()
            val bundle = Bundle()
            bundle.putString(KEY_PASSWORD, password)
            bundle.putString(KEY_LOGIN, login)
            fragment.arguments = bundle
            return fragment
        }

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