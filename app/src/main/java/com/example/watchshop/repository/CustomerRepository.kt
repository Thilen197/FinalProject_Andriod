package com.example.watchshop.repository

import com.example.watchshop.api.CustomerAPI
import com.example.watchshop.api.MyApiRequest
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Customer
import com.example.watchshop.response.CustomerResponse
import com.example.watchshop.response.LoginResponse

class CustomerRepository
    : MyApiRequest(){
    private val userAPI =
            ServiceBuilder.buildService(CustomerAPI::class.java)
    //register Customer
    suspend fun registerCustomer(customer: Customer): CustomerResponse {
        return apiRequest {
            userAPI.registercustomer(customer)
        }
    }
    //login Customer
    suspend fun checkCustomer(username:String,password:String): LoginResponse {
        return apiRequest {
            userAPI.checkcustomer(username,password)
        }
    }
}