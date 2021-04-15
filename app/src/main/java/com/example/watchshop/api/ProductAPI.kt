package com.example.watchshop.api

interface ProductAPI {

    @POST("order/insert")
    suspend fun addProduct(
        @Header("Authorization") token : String,
        @Body product : Product
    ) : Response<AddProductResponse>
    @POST("/image")
    suspend fun addImage(
        @Header("Authorization") token : String,
        @Body product : Product
    ) : Response<AddProductResponse>
    // get all students
    @GET("order/fetch")
    suspend fun getAllProduct(
        @Header("Authorixation") token : String
    ) : Response<AllProductResponse>

    //delete student
    @DELETE("order/delete/{id}")
    suspend fun deleteProduct(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ):Response<DeleteProductResponse>

    @Multipart
    @PUT("student/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path ("id") id:String,
        @Part file: MultipartBody.Part
    ):Response<ImageResponse>
}

}