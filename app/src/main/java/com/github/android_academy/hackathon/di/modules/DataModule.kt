package com.github.android_academy.hackathon.di.modules

import android.content.Context
import com.github.android_academy.hackathon.data.local.SampleDatabase
import com.github.android_academy.hackathon.data.repositories.SampleRepositoryImpl
import com.github.android_academy.hackathon.domain.repositories.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideSampleRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository

    @Provides
    @Singleton
    fun provideDatabase(appContext : Context) : SampleDatabase =
        SampleDatabase.getInstance(appContext)
}