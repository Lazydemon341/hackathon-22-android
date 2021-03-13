package com.github.android_academy.hackathon.domain.repositories

import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Course

interface CourseRepository {

    suspend fun getAllCourses(): OperationResult<List<Course>, String?>

    suspend fun getFavouriteCourses(username: String): OperationResult<List<Course>, String?>

    suspend fun updateCourse(course: Course) : OperationResult<Unit, String?>
}