package com.github.android_academy.hackathon.ui.loginfragment

import androidx.lifecycle.ViewModel
import com.github.android_academy.hackathon.domain.repositories.SampleRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val sampleRepository: SampleRepository
) : ViewModel() {


}