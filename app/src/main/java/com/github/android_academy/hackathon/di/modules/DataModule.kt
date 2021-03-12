package com.github.android_academy.hackathon.di.modules

import com.github.android_academy.hackathon.data.repositories.SampleRepositoryImpl
import com.github.android_academy.hackathon.domain.repositories.SampleRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideSampleRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository
}