package com.github.android_academy.hackathon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.android_academy.hackathon.data.local.dao.SampleDao

@Database(entities = [SampleDao::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun getSampleDao(): SampleDao

    companion object {
        private const val DATABASE_NAME = "sample_database"

        @Volatile
        private var instance: SampleDatabase? = null

        // May be redundant since we are using Dagger
        fun getInstance(context: Context): SampleDatabase =
            instance ?: synchronized(this) { instance ?: build(context) }

        private fun build(context: Context): SampleDatabase =
            Room.databaseBuilder(context, SampleDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}