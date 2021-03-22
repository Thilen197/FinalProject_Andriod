package com.example.watchshop.entity

import androidx.room.Entity

@Entity
data class Customer(
        val _id: String? = null,
        val username: String? = null,
        val mobile: String? = null,
        val email: String? = null,
        val password: String? = null

)

