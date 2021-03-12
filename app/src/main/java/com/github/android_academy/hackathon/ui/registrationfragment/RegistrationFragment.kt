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
import com.github.android_academy.hackathon.ui.ViewState

class RegistrationFragment : BaseFragment(R.layout.registration_fragment) {
    private val binding by viewBinding(RegistrationFragmentBinding::bind)

    private val viewModel: RegistrationViewModel by viewModels(
        factoryProducer = { RegistrationViewModelFactory() }
    )

    override fun initViews(view: View) {
        super.initViews(view)

        viewModel.registrationResult.observe(viewLifecycleOwner, { checkRegistrationResult(it)})

        binding.registrationFragmentSignUpButton.setOnClickListener {
            if (comparePasswords()){
                viewModel.register(
                    binding.registrationFragmentLogin.editText?.text.toString(),
                    binding.registrationFragmentPassword.editText?.text.toString(),
                    binding.registrationFragmentName.editText?.text.toString(),
                    binding.registrationFragmentIsmentorCheckbox.isChecked
                )
            }
            else{
                binding.registrationFragmentPassword2.error = getString(R.string.error_wrong_password) //покажем ошибку если пароли не совпали
            }
        }
    }

    fun comparePasswords():Boolean {
        return binding.registrationFragmentPassword.editText?.text == binding.registrationFragmentPassword2.editText?.text
    }

    fun checkRegistrationResult(it: ViewState<Unit, String>){
        //it.Success
        when(it){
            is ViewState.Loading -> {/*TODO показать анимацию */ }
            is ViewState.Error -> {
                //TODO в зависимости от сообщения подчеркивать разные поля
                wrongPassword(it.result)
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