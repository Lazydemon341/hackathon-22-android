package com.github.android_academy.hackathon.ui.courselist

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.CourseItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Course

class CourseViewHolder(private val binding: CourseItemFragmentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        course: Course,
        courseListener: (Course) -> Unit,
        addToFavoriteListener: (Course) -> Unit
    ) {
        setClickListeners(courseListener, addToFavoriteListener, course)
        setText(course)

        if (course.isSubscribed) {
            ImageViewCompat.setImageTintList(
                binding.courseFavouriteStar,
                ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.gold))
            )
        } else {
            ImageViewCompat.setImageTintList(
                binding.courseFavouriteStar,
                ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.gray))
            )
        }

        Glide.with(itemView)
            .load(course.imgUrl)
            .centerCrop()
            .placeholder(R.drawable.academy_logo)
            .into(binding.courseLogo)
    }

    private fun setClickListeners(
        courseListener: (Course) -> Unit,
        addToFavoriteListener: (Course) -> Unit,
        course: Course
    ) {
        itemView.setOnClickListener { courseListener(course) }
        binding.courseFavouriteStar.setOnClickListener {
            if (!course.isSubscribed) {
                ImageViewCompat.setImageTintList(
                    binding.courseFavouriteStar,
                    ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.gold))
                )
            } else {
                ImageViewCompat.setImageTintList(
                    binding.courseFavouriteStar,
                    ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.gray))
                )
            }
            addToFavoriteListener(course)
        }
    }

    private fun setText(course: Course) {
        binding.courseItemFragmentCoursename.text = course.title
        binding.courseItemTags.text = course.tags.joinToString { " " }
    }
}