package com.github.android_academy.hackathon.data.repositories

import androidx.lifecycle.LiveData
import com.github.android_academy.hackathon.data.PrefsStorage
import com.github.android_academy.hackathon.data.network.ServerApi
import com.github.android_academy.hackathon.data.network.models.LoginRequestDTO
import com.github.android_academy.hackathon.data.network.models.RegisterRequestDTO
import com.github.android_academy.hackathon.data.network.models.toUser
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.domain.repositories.MyOptional
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val prefsStorage: PrefsStorage,
    private val serverApi: ServerApi
) : AuthRepository {


    override fun loadUser(): User? = prefsStorage.loadUser()

    override fun observeUser(): LiveData<MyOptional<User>> = prefsStorage.observeUser()

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

            prefsStorage.saveToSharedPref(user)
            sendFcmTokenToBackend()

            OperationResult.Success(Unit)

        } catch (e: Exception) {
            OperationResult.Error(e.message)
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

            prefsStorage.saveToSharedPref(user)
            sendFcmTokenToBackend()

            OperationResult.Success(Unit)

        } catch (e: Exception) {
            OperationResult.Error(e.message)
        }

    private fun sendFcmTokenToBackend() {
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { fcmToken ->
                GlobalScope.launch {
                    println("fcm_token:$fcmToken")
                    serverApi.updateFcmToken(fcmToken)
                }
            }
            .addOnFailureListener {
                Timber.e(it, "Cannot get FCM token")
            }
    }

    override fun logOut() =
        prefsStorage.saveToSharedPref(null)
}

