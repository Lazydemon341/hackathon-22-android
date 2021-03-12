package com.github.android_academy.hackathon.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.android_academy.hackathon.data.local.entities.SampleEntity

@Dao
interface SampleDao {
    @Query("SELECT * FROM samples")
    fun getAll() : List<SampleEntity>
}