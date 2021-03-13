package com.github.android_academy.hackathon.ui.registrationfragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.RegistrationFragmentBinding
import com.github.android_academy.hackathon.di.viewmodels.registration.DaggerRegistrationViewModelComponent
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.ui.BaseFragment
import com.github.android_academy.hackathon.ui.ViewState
import kotlin.math.log

class RegistrationFragment : BaseFragment(R.layout.registration_fragment) {
    private val binding by viewBinding(RegistrationFragmentBinding::bind)

    private val viewModel: RegistrationViewModel by viewModels(
        factoryProducer = { RegistrationViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)
        viewModel.registrationResult.observe(viewLifecycleOwner, { checkRegistrationResult(it)})

        binding.registrationFragmentLogin.editText?.setText(arguments?.getString(KEY_LOGIN))
        binding.registrationFragmentPassword.editText?.setText(arguments?.getString(KEY_PASSWORD))

        binding.registrationFragmentSignUpButton.setOnClickListener {
            if (true){
                viewModel.register(
                    username = binding.registrationFragmentLogin.editText?.text.toString(),
                    password = binding.registrationFragmentPassword.editText?.text.toString(),
                    name = binding.registrationFragmentName.editText?.text.toString(),
                    isMentor = binding.registrationFragmentIsmentorCheckbox.isChecked
                )
            }
            else{
                binding.registrationFragmentPassword2.error = getString(R.string.error_wrong_password) //покажем ошибку если пароли не совпали
            }
        }
    }

    fun comparePasswords():Boolean {
        return binding.registrationFragmentPassword.editText?.text?.toString() ==
                binding.registrationFragmentPassword2.editText?.text?.toString()
    }

    fun checkRegistrationResult(it: ViewState<User, String?>){
        //it.Success
        when(it){
            is ViewState.Loading -> {/*TODO показать анимацию */ }
            is ViewState.Error -> {
                //TODO в зависимости от сообщения подчеркивать разные поля
                wrongPassword(it.result ?: "Unknown login error")
            }
            is ViewState.Success -> {
                //TODO запустить другой фрагмент
            }
        }
    }

    fun wrongPassword(message:String){
        binding.registrationFragmentLogin.error = message
    }

    companion object {
        private const val KEY_LOGIN = "login"
        private const val KEY_PASSWORD = "password"
        @JvmStatic
        fun newInstance(login:String, password:String):RegistrationFragment {
            val fragment = RegistrationFragment()
            val bundle = Bundle()
            bundle.putString(KEY_PASSWORD, password)
            bundle.putString(KEY_LOGIN, login )
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