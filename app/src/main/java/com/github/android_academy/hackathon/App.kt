package com.github.android_academy.hackathon

import android.app.Application
import com.github.android_academy.hackathon.di.AppComponent
import com.github.android_academy.hackathon.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var tree: Timber.Tree

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appContext(this)
            .apiUrl(BuildConfig.BASE_URL)
            .build()
        appComponent.inject(this)
        Timber.plant(tree)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}