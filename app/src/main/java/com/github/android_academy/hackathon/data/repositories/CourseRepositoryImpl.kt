package com.github.android_academy.hackathon.data.repositories

import com.github.android_academy.hackathon.data.network.ServerApi
import com.github.android_academy.hackathon.data.network.models.UpdateCourseRequestDTO
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.repositories.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : CourseRepository {

    override suspend fun getAllCourses(): OperationResult<List<Course>, String?> =
        try {
            val courses = serverApi.getAllCourses().map {
                Course(
                    id = it.id,
                    title = it.title,
                    shortDescription = it.shortDescription,
                    fullDescription = it.fullDescription,
                    imgUrl = it.imgUrl,
                    isSubscribed = it.isSubscribed,
                    tags = it.tags
                )
            }
            OperationResult.Success(courses)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }

    override suspend fun getFavouriteCourses(username: String): OperationResult<List<Course>, String?> =
        try {
            val courses = serverApi.getFavouriteCourses(/*username*/).map { //TODO: uncomment
                Course(
                    id = it.id,
                    title = it.title,
                    shortDescription = it.shortDescription,
                    fullDescription = it.fullDescription,
                    imgUrl = it.imgUrl,
                    isSubscribed = it.isSubscribed,
                    tags = it.tags
                )
            }
            OperationResult.Success(courses)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }

    override suspend fun updateCourse(course: Course) : OperationResult<Unit, String?> =
        try {
            serverApi.updateCourse(
                UpdateCourseRequestDTO(
                    id = course.id,
                    title = course.title,
                    shortDescription = course.shortDescription,
                    fullDescription = course.fullDescription,
                    imgUrl = course.imgUrl,
                    isSubscribed = course.isSubscribed,
                    tags = course.tags
                )
            )
            OperationResult.Success(Unit)
        }
        catch(e: Throwable){
            OperationResult.Error(e.message)
        }


}