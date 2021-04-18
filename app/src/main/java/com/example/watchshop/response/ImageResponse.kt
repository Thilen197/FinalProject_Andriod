package com.example.watchshop.response

import com.example.watchshop.entity.Product

data class ImageResponse(
    val success : Boolean? = null,
    val data:Product? = null
)
