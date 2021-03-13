package com.github.android_academy.hackathon.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.MyOptional
import javax.inject.Inject

class PrefsStorage @Inject constructor(
    context: Context
) {

    private val sharedPref = context.getSharedPreferences(
        context.resources.getString(R.string.shared_pref_name),
        Context.MODE_PRIVATE
    )

    private val userLiveData = MutableLiveData(MyOptional(loadUser()))

    fun observeUser(): LiveData<MyOptional<User>> = userLiveData

    fun loadUser(): User? {
        val username = sharedPref.getString(USERNAME_KEY, null)
        val name = sharedPref.getString(NAME_KEY, null)
        val token = sharedPref.getString(TOKEN_KEY, null)
        val isMentor = sharedPref.getBoolean(IS_MENTOR_KEY, false)

        if (!username.isNullOrBlank() && !name.isNullOrBlank() && !token.isNullOrBlank())
            return User(
                username = username,
                name = name,
                token = token,
                isMentor = isMentor
            )

        return null
    }

    fun saveToSharedPref(
        user: User?
    ) {
        if (user != null) {
            sharedPref.edit()
                .putString(USERNAME_KEY, user.username)
                .putString(NAME_KEY, user.name)
                .putString(TOKEN_KEY, user.token)
                .putBoolean(IS_MENTOR_KEY, user.isMentor)
                .apply()
        } else {
            sharedPref.edit()
                .remove(USERNAME_KEY)
                .remove(NAME_KEY)
                .remove(TOKEN_KEY)
                .remove(IS_MENTOR_KEY)
                .apply()
        }
        userLiveData.postValue(MyOptional(user))
    }

    companion object {
        private const val USERNAME_KEY = "username"
        private const val NAME_KEY = "name"
        private const val TOKEN_KEY = "token"
        private const val IS_MENTOR_KEY = "IsMentor"
    }
}
