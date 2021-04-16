package com.example.watchshop.repository

import com.example.watchshop.api.MyApiRequest
import com.example.watchshop.api.ProductAPI
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Product
import com.example.watchshop.response.AddProductResponse
import com.example.watchshop.response.DeleteProductResponse
import com.example.watchshop.response.GetProductResponse
import retrofit2.http.POST
import java.nio.file.Files.delete


class ProductRepository : MyApiRequest() {

    private val productAPI =
        ServiceBuilder.buildService(ProductAPI::class.java)

    //Add Student
    suspend fun addProduct(product: Product): AddProductResponse {
        return apiRequest {
            productAPI.addProduct(
//                    ServiceBuilder.token!!,
                product
            )
        }
    }

    suspend fun viewProduct(): GetProductResponse {
        return apiRequest {
            productAPI.getProduct()
        }
    }


    suspend fun deleteproduct(id: String): DeleteProductResponse {
        return apiRequest {
            productAPI.deleteproduct(
                ServiceBuilder.token!!, id
            )
        }
    }

    suspend fun updateproduct(id: String,body:Product): DeleteProductResponse {
        return apiRequest {
            productAPI.updateproduct(
                 id,body
            )
        }
    }
}

//        suspend fun addImage(product: Product): AddProductResponse {
//            return apiRequest {
//                productAPI.addProduct(
//                    ServiceBuilder.token!!, product
//                )
//            }
//        }





