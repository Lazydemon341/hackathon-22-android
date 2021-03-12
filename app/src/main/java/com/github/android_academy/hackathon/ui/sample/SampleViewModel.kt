package com.github.android_academy.hackathon.ui.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.android_academy.hackathon.domain.models.Sample
import com.github.android_academy.hackathon.domain.repositories.SampleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    private val sampleRepository: SampleRepository
) : ViewModel() {
    private val _sample: MutableLiveData<Sample> = MutableLiveData()
    val sample: LiveData<Sample>
        get() =
            _sample

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable>
        get() =
            _error

    init {
        viewModelScope.launch {
            runCatching {
                sampleRepository.loadSample(id = 1)
            }.onSuccess {
                _sample.value = it
            }.onFailure {
                _error.value = it
            }
        }
    }
}