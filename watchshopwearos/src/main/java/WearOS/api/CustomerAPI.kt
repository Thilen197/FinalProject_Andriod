package WearOS.api

import WearOS.entity.Customer
import WearOS.response.CustomerResponse
import WearOS.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CustomerAPI {
    @POST("/customer/register")
    suspend fun registercustomer(
        @Body customer : Customer
    ): Response<CustomerResponse>

    //Login User

    @FormUrlEncoded
    @POST("/customer/login")
    suspend fun checkcustomer(
        @Field("username") username : String,
        @Field("Password") Password: String
    ) : Response<LoginResponse>
}