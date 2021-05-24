package com.topanlabs.filmtopan.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ShowtaimentEntity::class], version = 1, exportSchema = false)
abstract class ShowtaimentDatabase : RoomDatabase() {
    abstract fun artDao(): ShowtaimentDao

    companion object {
        @Volatile
        private var INSTANCE: ShowtaimentDatabase? = null

        fun getDatabase(context: Context): ShowtaimentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowtaimentDatabase::class.java,
                    "art_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}