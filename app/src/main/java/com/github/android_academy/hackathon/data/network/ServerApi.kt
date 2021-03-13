package com.github.android_academy.hackathon.data.network

import com.github.android_academy.hackathon.data.network.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServerApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequestDTO
    ): LoginResponseDTO

    @POST("register")
    suspend fun register(
        @Body registerRequestDTO: RegisterRequestDTO
    ): LoginResponseDTO

    @GET("courses/favorite")
    suspend fun getFavouriteCourses(username: String): List<CourseDTO>

    @GET("courses/all")
    suspend fun getAllCourses(): List<CourseDTO>

    @POST("courses/update")
    suspend fun updateCourse(
        @Body updateCourseRequestDTO: UpdateCourseRequestDTO
    )

    @POST("lectures/update")
    suspend fun updateLecture(
        @Body updateLectureRequestDTO: UpdateLectureRequestDTO
    )

    @GET("lectures/all")
    suspend fun getAllLectures(
        @Query(value = "courseId") courseId: Long
    )
}