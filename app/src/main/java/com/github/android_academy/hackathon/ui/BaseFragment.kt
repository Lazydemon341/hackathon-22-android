package com.github.android_academy.hackathon.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.android_academy.hackathon.R
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseFragment(layoutResource: Int, private val isShowToolbar: Boolean) :
    Fragment(layoutResource) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        showToolbar()
    }

    open fun initViews(view: View) {}

    open fun onBackPressed() {}

    private fun showToolbar() {
        activity?.findViewById<MaterialToolbar>(R.id.app_toolbar)?.isVisible = isShowToolbar
    }
}
