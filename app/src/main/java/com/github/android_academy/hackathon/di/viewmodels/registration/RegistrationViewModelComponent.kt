package com.github.android_academy.hackathon.di.viewmodels.registration

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.registrationfragment.RegistrationViewModel
import dagger.Component

@VM
@Component(dependencies = [AppComponent::class])
interface RegistrationViewModelComponent {
    fun provideViewModel(): RegistrationViewModel
}