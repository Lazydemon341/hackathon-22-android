package com.github.android_academy.hackathon.data.repositories

import android.content.Context
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.data.local.SampleDatabase
import com.github.android_academy.hackathon.data.network.ServerApi
import com.github.android_academy.hackathon.data.network.models.LoginRequestDTO
import com.github.android_academy.hackathon.data.network.models.RegisterRequestDTO
import com.github.android_academy.hackathon.data.network.models.toUser
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val context: Context,
    private val database: SampleDatabase,
    private val serverApi: ServerApi
) :
    AuthRepository {

    private val sharedPref = context.getSharedPreferences(
        context.resources.getString(R.string.shared_pref_name),
        Context.MODE_PRIVATE
    )

    override fun loadUser(): User? {
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

    override suspend fun login(
        username: String,
        password: String
    ): OperationResult<Unit, String?> =
        try {
            val user = serverApi.login(
                LoginRequestDTO(
                    username = username,
                    pwd = password
                )
            ).toUser()

            saveToSharedPrefs(user)

            OperationResult.Success(Unit)
        } catch (e: Exception) {
            OperationResult.Error(e.message)
        }

    private fun saveToSharedPrefs(user: User) {
        sharedPref.edit()
            .putString(USERNAME_KEY, user.username)
            .putString(NAME_KEY, user.name)
            .putString(TOKEN_KEY, user.token)
            .putBoolean(IS_MENTOR_KEY, user.isMentor)
            .apply()
    }

    override suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, String?> =
        try {
            val user = serverApi.register(
                RegisterRequestDTO(
                    username = username,
                    pwd = password,
                    name = name,
                    isMentor = isMentor
                )
            ).toUser()

            saveToSharedPrefs(user)

            OperationResult.Success(Unit)
        } catch (e: Exception) {
            OperationResult.Error(e.message)
        }
}

private const val USERNAME_KEY = "username"
private const val NAME_KEY = "name"
private const val TOKEN_KEY = "token"
private const val IS_MENTOR_KEY = "isMentor"