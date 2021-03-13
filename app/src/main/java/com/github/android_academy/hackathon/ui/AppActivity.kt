package com.github.android_academy.hackathon.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.data.PrefsStorage
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class AppActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var prefsStorage: PrefsStorage

    private val navigator = AppNavigator(this, R.id.container)

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTheme(R.style.Theme_HackathonWinnerApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        if (savedInstanceState == null) {
            if (prefsStorage.authToken.isNullOrBlank())
                router.newRootScreen(Screens.loginFragment())
            else
                router.newRootScreen(Screens.courseListFragment())
        }
    }

    override fun attachBaseContext(newBase: Context) {
        App.appComponent.inject(this)
        super.attachBaseContext(newBase)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}