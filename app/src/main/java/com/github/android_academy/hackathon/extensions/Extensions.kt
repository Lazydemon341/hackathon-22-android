@file:Suppress("NOTHING_TO_INLINE")

package com.github.android_academy.hackathon.extensions

import retrofit2.HttpException

inline fun Exception.isBadRequest(): Boolean =
    this is HttpException && this.code() == 400

inline fun Exception.isConflict(): Boolean =
    this is HttpException && this.code() == 409