package com.github.android_academy.hackathon.domain.repositories

import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.Lecture

interface CourseRepository {

    suspend fun getAllCourses(): OperationResult<List<Course>, String?>

    suspend fun getFavouriteCourses(username: String): OperationResult<List<Course>, String?>

    suspend fun updateCourse(course: Course) : OperationResult<Unit, String?>

    suspend fun getAllLectures(courseId: Long) : OperationResult<List<Lecture>, String?>

    suspend fun updateLecture(lecture: Lecture) : OperationResult<Unit, String?>
}