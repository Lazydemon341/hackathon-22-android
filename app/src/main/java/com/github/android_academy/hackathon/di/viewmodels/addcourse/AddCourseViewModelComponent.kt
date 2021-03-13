package com.github.android_academy.hackathon.di.viewmodels.addcourse

import com.bsquaredwifi.app.di.scopes.VM
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.ui.courselist.CourseListViewModel
import dagger.Component
@VM
@Component(dependencies = [AppComponent::class])
interface AddCourseViewModelComponent {
    fun provideViewModel(): CourseListViewModel
}