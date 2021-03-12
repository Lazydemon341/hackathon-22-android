package com.github.android_academy.hackathon.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.android_academy.hackathon.domain.models.Sample

@Entity(tableName = "samples")
class SampleEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "text") val text: String
)


fun SampleEntity.toSample(): Sample =
    Sample(
        id = this.id,
        text = this.text
    )