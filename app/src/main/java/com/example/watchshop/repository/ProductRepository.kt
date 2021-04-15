package com.example.watchshop.repository

import com.example.watchshop.api.MyApiRequest
import com.example.watchshop.api.ProductAPI
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Product
import com.example.watchshop.response.AddProductResponse


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
//        suspend fun addImage(product: Product): AddProductResponse {
//            return apiRequest {
//                productAPI.addProduct(
//                    ServiceBuilder.token!!, product
//                )
//            }
//        }

        }



