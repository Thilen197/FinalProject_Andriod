package com.example.watchshop.response

import com.example.watchshop.entity.Customer

data class CustomerResponse(
        val data: Customer? = null,
        val success: Boolean? = null
)

