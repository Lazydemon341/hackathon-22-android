package com.github.android_academy.hackathon.ui.lectureslist

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.android_academy.hackathon.R
import com.github.android_academy.hackathon.databinding.LecturesItemFragmentBinding
import com.github.android_academy.hackathon.domain.models.Course
import com.github.android_academy.hackathon.domain.models.Lecture

class LectureViewHolder(
    private val binding : LecturesItemFragmentBinding
    ): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        lectureListener: (Lecture) -> Unit,
        lecture: Lecture
    ) {
        setClickListener(lectureListener, lecture)
    }

    private fun setClickListener(
        lectureListener: (Lecture) -> Unit,
        lecture: Lecture
    ){
        itemView.setOnClickListener {
            lectureListener(lecture)
        }
    }

    private fun setText(lecture: Lecture){
        binding.lectureItemFragmentTitle.text = lecture.title
        binding.lectureItemFragmentTags.text = lecture.tags.joinToString(" ")
        Glide
            .with(binding.root.context)
            .load(lecture.imgUrl)
            .centerCrop()
            //.placeholder(R.)
            .into(binding.lectureItemFragmentImgurl)
    }
}