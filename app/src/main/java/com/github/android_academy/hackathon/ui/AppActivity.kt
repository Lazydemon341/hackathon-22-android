package com.github.android_academy.hackathon.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.android_academy.hackathon.App
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.Screens
import com.github.android_academy.hackathon.databinding.AppActivityBinding
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    private val binding by viewBinding(AppActivityBinding::bind)


    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var authRepository: AuthRepository

    private val navigator = AppNavigator(this, R.id.container)

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTheme(R.style.Theme_HackathonWinnerApp)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.app_activity)
        setSupportActionBar(findViewById(R.id.app_toolbar))
        setupActionBar()

        if (!handleIntent(intent) && savedInstanceState == null) {
            if (authRepository.loadUser() == null)
                router.newRootScreen(Screens.loginFragment())
            else
                router.newRootScreen(Screens.courseListFragment())
        }

        authRepository.observeUser().observe(this) {
            if (it.value != null) {
                ((binding.nvView.getHeaderView(0) as LinearLayout).getChildAt(0) as TextView).text =
                    it.value.username
                ((binding.nvView.getHeaderView(0) as LinearLayout).getChildAt(1) as TextView).text =
                    it.value.name
            }
        }

        binding.nvView.setNavigationItemSelectedListener {
            when (it.itemId) {
                (R.id.item_log_out) -> {
                    authRepository.logOut()
                    router.newRootScreen(Screens.loginFragment())
                }
                (R.id.item_about) -> {
                    //TODO
                }
                else -> {
                    //TODO
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun handleIntent(receivedIntent: Intent?): Boolean {
        receivedIntent?.data ?: (receivedIntent?.extras?.getString("click_action")
            ?.let { Uri.parse(it) })?.also { uri ->
                val courseId = uri.getQueryParameter("courseId")?.toLong() ?: -1
                val lectureId = uri.getQueryParameter("lectureId")?.toLong() ?: -1
                router.newRootChain(
                    Screens.courseListFragment(),
                    Screens.lecturesListFragment(courseId),
                    Screens.lectureFragment(lectureId = lectureId)
                )
                return true
            }
        return false
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


    private fun setupActionBar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        (binding.appToolbar).setNavigationOnClickListener() {
            findViewById<DrawerLayout>(R.id.drawer_layout).openDrawer(GravityCompat.START)
        }
    }

}