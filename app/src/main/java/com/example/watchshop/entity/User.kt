package com.example.watchshop.entity

data class User(

        var username: String? = null,
        var phone: String? = null,
        var password: String? = null
) {
    @primarykey(autoGenerate = true)
    var userId: Int = 0
}
