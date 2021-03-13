package com.github.android_academy.hackathon.ui.courselist

import androidx.recyclerview.widget.RecyclerView
import com.github.android_academy.hackathon.databinding.CourseItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Course

class CourseViewHolder(private val binding : CourseItemFragmentBinding):
        RecyclerView.ViewHolder(binding.root) {

    fun bind(course : Course, listener:(Course)-> Unit){
        setClickListener(listener,course)
        setText(course)
    }

    private fun setClickListener(
            listener: (Course) -> Unit,
            course: Course
    ){
        itemView.setOnClickListener { listener(course) }
    }

    private fun setText(course: Course){
        binding.courseItemFragmentCoursename.text = course.name
        binding.courseItemFragmentLecturescount.text = course.lecturesCount.toString()
    }

}