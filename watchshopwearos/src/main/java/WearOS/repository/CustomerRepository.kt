package WearOS.repository

import WearOS.api.CustomerAPI
import WearOS.api.MyApiRequest
import WearOS.api.ServiceBuilder
import WearOS.entity.Customer
import WearOS.response.CustomerResponse
import WearOS.response.LoginResponse

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