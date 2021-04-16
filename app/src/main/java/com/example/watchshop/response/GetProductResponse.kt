package com.example.watchshop.response

import com.example.watchshop.entity.Product

data class GetProductResponse(
        val success : Boolean?= null,
        val productdata : List<Product>? = null,
        val message:String? =null
)
