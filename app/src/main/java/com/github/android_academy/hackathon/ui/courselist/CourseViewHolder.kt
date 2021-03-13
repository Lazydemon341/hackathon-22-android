package com.github.android_academy.hackathon.ui.courselist

import androidx.recyclerview.widget.RecyclerView
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.CourseItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Course

class CourseViewHolder(private val binding : CourseItemFragmentBinding):
        RecyclerView.ViewHolder(binding.root) {

    fun bind(
        course : Course,
        courseListener:(Course)-> Unit,
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
        binding.imageButton.setOnClickListener {
            addToFavoriteListener(course) // Изменить курс

            binding.imageButton.setImageResource(
                //TODO отправить все кроме id
                if (course.isSubscribed) R.drawable.ic_round_star_24
                else R.drawable.ic_round_star_border_24) }
    }

    private fun setText(course: Course){
        binding.courseItemFragmentCoursename.text = course.title
        binding.courseItemFragmentLecturescount.text = course.shortDescription
        binding.imageButton.setImageResource(
            if (course.isSubscribed) R.drawable.ic_round_star_24
            else R.drawable.ic_round_star_border_24)
    }
}