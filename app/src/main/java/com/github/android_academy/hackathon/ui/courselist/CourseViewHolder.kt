package com.github.android_academy.hackathon.ui.courselist

import androidx.recyclerview.widget.RecyclerView
import com.github.android_academy.hackathon.databinding.CourseItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Course

class CourseViewHolder(private val binding : CourseItemFragmentBinding):
        RecyclerView.ViewHolder(binding.root) {

    fun bind(
        course : Course, courseListener:(Course)-> Unit,
        addToFavoriteListener : (Course) -> Unit
    ) {
        setClickListeners(courseListener, addToFavoriteListener,course)
        setText(course)
    }

    private fun setClickListeners(
            courseListener: (Course) -> Unit,
            addToFavoriteListener: (Course) -> Unit,
            course: Course
    ){
        itemView.setOnClickListener { courseListener(course) }
        binding.imageButton.setOnClickListener { addToFavoriteListener(course) }
    }

    private fun setText(course: Course){
        binding.courseItemFragmentCoursename.text = course.title
        //binding.courseItemFragmentLecturescount.text = course.lecturesCount.toString()
    }

}