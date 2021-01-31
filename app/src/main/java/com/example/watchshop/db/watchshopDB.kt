package com.example.watchshop.db

import android.content.Context
import com.example.watchshop.dao.UserDAO

abstract class watchshopDB {
    abstract fun getUserDAO(): UserDAO
    companion object {
        @Volatile
        private var instance: watchshopDB? = null
        fun getInstance(context: Context): watchshopDB {
            if (instance == null) {
                synchronized(watchshopDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        WatchshopDB::class.java,
                        "Watchshop Database"
                ).fallbackToDestructiveMigration()
                        .build()
    }


}