package com.example.watchshop.response

import com.example.watchshop.entity.Customer

data class LoginResponse(
        val success:Boolean?=null,
        val Token:String?=null,
        val data:Customer?=null
) {
}