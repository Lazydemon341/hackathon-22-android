package com.github.android_academy.hackathon.di.modules

import com.github.android_academy.hackathon.data.repositories.AuthRepositoryImpl
import com.github.android_academy.hackathon.data.repositories.CourseRepositoryImpl
import com.github.android_academy.hackathon.data.repositories.LecturesRepositoryImpl
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import com.github.android_academy.hackathon.domain.repositories.LecturesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun provideCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository

    @Binds
    @Singleton
    abstract fun provideLectureRepository(lectureRepository: LecturesRepositoryImpl): LecturesRepository
}