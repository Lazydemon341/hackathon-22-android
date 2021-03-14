package com.github.android_academy.hackathon.ui.login

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LoginFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.login.DaggerLoginViewModelComponent
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.ViewState

class LoginFragment : BaseFragment(R.layout.login_fragment, false) {
    private val binding by viewBinding(LoginFragmentBinding::bind)


    private val viewModel: LoginViewModel by viewModels(
        factoryProducer = { LoginViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        viewModel.loginResult.observe(viewLifecycleOwner, { checkLoginResult(it) })

        binding.loginFragmentPassword.editText?.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                viewModel.login(
                    binding.loginFragmentLogin.editText?.text.toString(),
                    binding.loginFragmentPassword.editText?.text.toString()
                )
                true
            } else false
        }

        binding.loginFragmentSignInButton.setOnClickListener {
            viewModel.login(
                binding.loginFragmentLogin.editText?.text.toString(),
                binding.loginFragmentPassword.editText?.text.toString()
            )
        }
        //TODO добавить помогающий текст 

        binding.loginFragmentSignUpButton.setOnClickListener {
            viewModel.launchRegistration(
                binding.loginFragmentLogin.editText?.text.toString(),
                binding.loginFragmentPassword.editText?.text.toString()
            )
        }

        bindHideErrors()
    }

    private fun checkLoginResult(it: ViewState<Unit, String?>) {
        //it.Success
        when (it) {
            is ViewState.Loading -> {
                binding.loginProgressBar.isVisible = true
            }
            is ViewState.Error -> {
                binding.loginProgressBar.isVisible = false
                wrongPassword("Wrong username or password!")
            }
            is ViewState.Success -> {
                binding.loginProgressBar.isVisible = true
                viewModel.launchCourseList()
            }
        }
    }

    private fun wrongPassword(message: String) {
        binding.loginFragmentPassword.error = message
    }

    override fun onBackPressed() {
        viewModel.exitFragment()
    }

    private fun bindHideErrors() {
        //ошибка исчезнет при изменении текста логина
        binding.loginFragmentLogin.addOnEditTextAttachedListener {
            binding.loginFragmentLogin.error = null
        }

        //ошибка исчезнет при изменении текста пароля
        binding.loginFragmentPassword.addOnEditTextAttachedListener {
            binding.loginFragmentPassword.error = null
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
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