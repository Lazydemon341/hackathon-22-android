package com.github.android_academy.hackathon.data

import android.content.Context
import android.content.SharedPreferences
import com.github.android_academy.hackathon.domain.models.User
import com.squareup.moshi.Moshi
import javax.inject.Inject

class PrefsStorage @Inject constructor(
    private val context: Context,
    private val moshi: Moshi
) {

    var authToken: String?
        get() = getSharedPreferences(NAME_AUTH).getString(KEY_TOKEN, null)
        set(token) = getSharedPreferences(NAME_AUTH)
            .edit()
            .putString(KEY_TOKEN, token)
            .apply()

    var cachedProfile: User?
        get() {
            val profileJson = getSharedPreferences(NAME_PROFILE).getString(KEY_PROFILE, null)
            return if (profileJson != null) {
                profileJson.runCatching {
                    moshi.adapter(User::class.java)
                        .fromJson(this)
                }.getOrNull()
            } else {
                null
            }
        }
        set(profile) = getSharedPreferences(NAME_PROFILE)
            .edit()
            .putString(KEY_PROFILE, moshi.adapter(User::class.java).toJson(profile))
            .apply()

    fun clearProfile() {
        getSharedPreferences(NAME_PROFILE).edit().clear().apply()
        cachedProfile = null
    }

    private fun getSharedPreferences(name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    companion object {
        const val NAME_AUTH = "auth_data"
        const val KEY_TOKEN = "token"
        const val NAME_PROFILE = "profile"
        const val KEY_PROFILE = "profile"
    }
}
