package com.github.android_academy.hackathon.di.viewmodels.timepicker

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.timepicker.TimePickerViewModel
import dagger.Component

@VM
@Component(dependencies = [AppComponent::class])
interface TimePickerViewModelComponent {
    fun provideViewModel(): TimePickerViewModel
}