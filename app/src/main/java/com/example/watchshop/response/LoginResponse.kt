package com.example.watchshop.response

import com.example.watchshop.entity.User

data class LoginResponse(
        val Success:Boolean?=null,
        val Data:User?=null,
        val Message:String?=null
) {
}