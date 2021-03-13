package com.github.android_academy.hackathon.ui.courselistfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.android_academy.hackathon.databinding.CourseItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Course

class CoursesAdapter(
        private val listener: (Course) -> Unit //Обозначает листенера для нажатия на курс
) : ListAdapter<Course, CourseViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CourseItemFragmentBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Course>(){
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean
            = oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean
            = oldItem == newItem

        }
    }
}