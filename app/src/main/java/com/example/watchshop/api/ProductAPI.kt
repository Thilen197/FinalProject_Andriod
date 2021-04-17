package com.example.watchshop.api

import androidx.room.Delete
import com.example.watchshop.entity.Product
import com.example.watchshop.response.AddProductResponse
import com.example.watchshop.response.DeleteProductResponse
import com.example.watchshop.response.GetProductResponse
import com.example.watchshop.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ProductAPI {

    @POST("/product/insert")
    suspend fun addProduct(
//        @Header("Authorization") token : String,
        @Body product : Product
    ) : Response<AddProductResponse>

    @GET("product/fetchall")
    suspend fun getProduct(
//            @Header("Authorization") token: String,
    ):Response<GetProductResponse>

//Delete product

    @DELETE("product/delete/{id}")
    suspend fun deleteproduct(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ):Response<DeleteProductResponse>


    @POST("/image/{id}")
    suspend fun addImage(
        @Header("Authorization") token : String,
        @Body product : Product
    ) : Response<AddProductResponse>

    @PUT("product_update/{id}")
    suspend fun updateproduct(
        @Path ("id")id: String,
        @Body product : Product
    ) : Response<DeleteProductResponse>



    @Multipart
    @PUT("student/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path ("id") id:String,
        @Part file: MultipartBody.Part
    ):Response<ImageResponse>
}

