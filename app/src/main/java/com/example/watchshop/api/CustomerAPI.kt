package com.example.watchshop.api

import com.example.watchshop.entity.Customer
import com.example.watchshop.response.CustomerResponse
import com.example.watchshop.response.LoginResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CustomerAPI {
    @POST("Customer/insert")
    suspend fun registercustomer(
            @Body customer : Customer
    ):Response <CustomerResponse>

    //Login User

    @FormUrlEncoded
    @POST("customer/Login")
    suspend fun checkcustomer(
            @Field("username") username : String,
            @Field("Password") Password: String
    ) : Response <LoginResponse>
}