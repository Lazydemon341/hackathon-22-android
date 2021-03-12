package com.github.android_academy.hackathon.di

import android.content.Context
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.di.modules.AppModule
import com.github.android_academy.hackathon.di.modules.DataModule
import com.github.android_academy.hackathon.di.modules.NetworkModule
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.ui.AppActivity
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {
    fun provideRouter(): Router

    fun provideSampleRepository(): AuthRepository

    fun inject(app: App)

    fun inject(appActivity: AppActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun apiUrl(@Named(NetworkModule.BASE_URL) url: String): Builder

        @BindsInstance
        fun appContext(context: Context): Builder
    }
}
