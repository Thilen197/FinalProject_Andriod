package com.example.watchshop.response

import com.example.watchshop.entity.Product

data class AddProductResponse (
    val success : Boolean?= null,
    val data : Product? = null,
    val message:String? =null

)
