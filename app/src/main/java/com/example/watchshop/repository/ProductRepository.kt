package com.example.watchshop.repository

import com.example.watchshop.api.MyApiRequest
import com.example.watchshop.api.ServiceBuilder

class ProductRepository : MyApiRequest {

        private val productAPI =
            ServiceBuilder.buildService(ProductAPI::class.java)

        //Add Student
        suspend fun addProduct(product: Product): AddProductResponse {
            return apiRequest {
                productAPI.addProduct(
                    ServiceBuilder.token!!, product
                )
            }
        }
        suspend fun addImage(product: Product): AddProductResponse {
            return apiRequest {
                productAPI.addProduct(
                    ServiceBuilder.token!!, product
                )
            }
        }
        suspend fun getAllProduct(): AllProductResponse {
            return apiRequest {
                productAPI.getAllProduct(
                    ServiceBuilder.token!!
                )
            }
        }
        suspend fun deleteProduct(id: String): DeleteProductResponse {
            return apiRequest {
                productAPI.deleteProduct(
                    ServiceBuilder.token!!,id
                )
            }
        }
        suspend fun uploadImage(id: String, body: MultipartBody.Part)
                : ImageResponse {
            return apiRequest {
                productAPI.uploadImage(ServiceBuilder.token!!, id, body)
            }
        }

    }

}