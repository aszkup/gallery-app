package com.android.galleryapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//
//@Database(entities = [], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context): AppDatabase {
//            return Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()
//        }
//    }
//}
