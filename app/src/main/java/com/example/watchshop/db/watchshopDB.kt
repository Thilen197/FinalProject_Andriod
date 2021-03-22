//package com.example.watchshop.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.watchshop.dao.UserDAO
//import com.example.watchshop.entity.Customer
//
//@Database(
//    entities = [(Customer::class)],
//    version = 2,
//    exportSchema = false
//)
//abstract class watchshopDB : RoomDatabase(){
//    abstract fun getUserDAO(): UserDAO
//    companion object {
//        @Volatile
//        private var instance: watchshopDB? = null
//        fun getInstance(context: Context): watchshopDB {
//            if (instance == null) {
//                synchronized(watchshopDB::class) {
//                    instance = buildDatabase(context)
//                }
//            }
//            return instance!!
//        }
//        private fun buildDatabase(context: Context) =
//                Room.databaseBuilder(
//                        context.applicationContext,
//                        watchshopDB::class.java,
//                        "Watchshop Database"
//                ).fallbackToDestructiveMigration()
//                        .build()
//    }
//
//
//}