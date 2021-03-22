//package com.example.watchshop.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.example.watchshop.entity.Customer
//
//@Dao
//interface UserDAO {
//
//    @Insert
//    suspend fun registerUser(customer : Customer)
//
//    @Query("select * from Customer where username=(:username) and password=(:password)")
//    suspend fun checkUser(username: String, password: String): Customer
//
//    // @Delete
//    //suspend fun deleteUser(user : User)
//    // @Update
//    //suspend fun updateUser(user : User)
//}