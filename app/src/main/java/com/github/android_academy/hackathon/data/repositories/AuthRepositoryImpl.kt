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
import com.github.android_academy.hackathon.extensions.isConflict
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val context: Context,
    private val database: SampleDatabase,
    private val serverApi: ServerApi
) :
    AuthRepository {

    override fun loadUser(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun login(
        username: String,
        password: String
    ): OperationResult<Unit, String> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<User, String?> =
        withContext(Dispatchers.IO) {
            try {
                OperationResult.Success(
                    data = serverApi.register(
                        RegisterRequestDTO(
                            username = username,
                            pwd = password,
                            name = name,
                            isMentor = isMentor
                        )
                    ).toUser()
                )
            } catch (e: Exception) {
                if (e.isConflict()) {
                    OperationResult.Error(e.message)
                } else {
                    throw e
                }
            }
//            val sharedPref = context.getSharedPreferences(
//                context.resources.getString(R.string.shared_pref_name),
//                Context.MODE_PRIVATE
//            )
//            sharedPref.edit()
//                .putString("username", username)
//                .putString("password", password)
//                .putString("name", name)
//                .putBoolean("isMentor", isMentor)
//                .apply()
        }
}