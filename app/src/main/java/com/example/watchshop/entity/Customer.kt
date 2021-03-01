package com.example.watchshop.entity

import androidx.room.Entity

@Entity
data class Customer(
        val _id:String,
        val Customername:String?=null
) {

}