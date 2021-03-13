package com.github.android_academy.hackathon.di.viewmodels.login

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.login.LoginViewModel
import dagger.Component

@VM
@Component(dependencies = [AppComponent::class])
interface LoginViewModelComponent {
    fun provideViewModel(): LoginViewModel
}