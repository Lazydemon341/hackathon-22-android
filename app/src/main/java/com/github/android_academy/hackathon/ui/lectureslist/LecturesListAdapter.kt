package com.github.android_academy.hackathon.ui.lectureslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.android_academy.hackathon.databinding.LecturesItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Lecture

class LecturesListAdapter(
    private val callback: (Lecture) -> Unit
): ListAdapter<Lecture, LectureViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LecturesItemFragmentBinding.inflate(layoutInflater, parent, false)
        return LectureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        holder.bind(callback,getItem(position))
    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Lecture>() {
            override fun areItemsTheSame(oldItem: Lecture, newItem: Lecture): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Lecture, newItem: Lecture): Boolean =
                oldItem == newItem

        }
    }
}