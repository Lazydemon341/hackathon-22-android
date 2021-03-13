package com.github.android_academy.hackathon.di.viewmodels.lecture

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.lecture.LectureViewModel
import dagger.Component

@VM
@Component(dependencies = [AppComponent::class])
interface LectureViewModelComponent {
    fun provideViewModel(): LectureViewModel
}