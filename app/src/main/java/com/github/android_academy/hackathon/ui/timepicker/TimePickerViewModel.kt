package com.github.android_academy.hackathon.ui.timepicker

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class TimePickerViewModel @Inject constructor(
    private val router:Router
) : ViewModel() {

    fun exitFragment(){
        router.exit()
    }
}