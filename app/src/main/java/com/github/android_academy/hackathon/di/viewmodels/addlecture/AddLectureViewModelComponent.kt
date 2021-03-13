package com.github.android_academy.hackathon.di.viewmodels.addlecture

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.addlecture.AddLectureViewModel
import dagger.Component

@VM
@Component(dependencies = [AppComponent::class])
interface AddLectureViewModelComponent {
    fun provideViewModel(): AddLectureViewModel
}
