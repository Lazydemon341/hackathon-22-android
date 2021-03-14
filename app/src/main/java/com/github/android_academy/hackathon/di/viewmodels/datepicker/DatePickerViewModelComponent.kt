package com.github.android_academy.hackathon.di.viewmodels.datepicker

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.datepicker.DatePickerViewModel
import dagger.Component

@VM
@Component(dependencies = [AppComponent::class])
interface DatePickerViewModelComponent {
    fun provideViewModel():DatePickerViewModel
}