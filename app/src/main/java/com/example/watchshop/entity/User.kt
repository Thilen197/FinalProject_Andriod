package com.example.watchshop.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class User(

        var name :String? = null,
        var username: String? = null,
        var phone: String? = null,
        var password: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}
