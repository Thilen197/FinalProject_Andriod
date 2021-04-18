package com.example.watchshop.api

import com.example.watchshop.entity.Customer
import com.example.watchshop.response.CustomerResponse
import com.example.watchshop.response.GetProductResponse
import com.example.watchshop.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface CustomerAPI {
    @POST("/customer/register")
    suspend fun registercustomer(
            @Body customer : Customer
    ):Response <CustomerResponse>

    //Login User

    @FormUrlEncoded
    @POST("/customer/login")
    suspend fun checkcustomer(
            @Field("username") username : String,
            @Field("Password") Password: String
    ) : Response <LoginResponse>

    @GET("product/fetchall")
    suspend fun viewprofile(
//            @Header("Authorization") token: String,
    ):Response<GetProductResponse>
}