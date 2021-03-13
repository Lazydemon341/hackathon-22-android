package com.github.android_academy.hackathon.di.modules

import com.github.android_academy.hackathon.data.network.ServerApi
import com.github.android_academy.hackathon.data.network.TokenAuthenticator
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthenticator(tokenAuthenticator: TokenAuthenticator): Authenticator =
        tokenAuthenticator

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authenticator: Authenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient().newBuilder()
            .authenticator(authenticator)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
//            .addInterceptor(AuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ServerApi = retrofit.create(ServerApi::class.java)

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    companion object {
        const val BASE_URL = "baseUrl"
    }
}

//private class AuthInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val newUrl = chain.request().url
//            .newBuilder()
//            .addQueryParameter("api_key", BuildConfig.API_KEY)
//            .build()
//
//        val newRequest = chain.request()
//            .newBuilder()
//            .url(newUrl)
//            .build()
//
//        return chain.proceed(newRequest)
//    }
//}