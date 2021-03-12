package com.github.android_academy.hackathon.di.modules

import com.github.android_academy.hackathon.data.repositories.AuthRepositoryImpl
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideSampleRepository(sampleRepositoryImpl: AuthRepositoryImpl): AuthRepository
}