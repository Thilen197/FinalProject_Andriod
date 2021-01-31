package com.example.watchshop.dao

import com.example.watchshop.entity.User

interface UserDAO {

    @Insert
    suspend fun registerUser(user : User)

    @Query("select * from User where username=(:username) and password=(:password)")
    suspend fun checkUser(username: String, password: String): User

    // @Delete
    //suspend fun deleteUser(user : User)
    // @Update
    //suspend fun updateUser(user : User)
}