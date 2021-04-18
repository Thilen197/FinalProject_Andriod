package com.example.watchshop

import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Customer
import com.example.watchshop.entity.Product
import com.example.watchshop.repository.CustomerRepository
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Test


class WatchshopTest {
    private lateinit var customerRepo: CustomerRepository;

    @Test
    fun loginTest() = runBlocking {
        customerRepo = CustomerRepository();
        val response = customerRepo.checkCustomer("thilen2021", "thilen2021")
        val expectedResult: Boolean = true;
        val actualResult = response.success;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    fun loginTestFailed() = runBlocking {
        customerRepo = CustomerRepository();
        val response = customerRepo.checkCustomer("kjhaasd", "thilen20asdf")
        val expectedResult: Boolean = true;
        val actualResult = response.success;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    fun signUpTest() = runBlocking {
        customerRepo = CustomerRepository();
        val Customer = Customer(
            username = "thilen5",
            mobile = "9849226662",
            email = "thilen@gmail.com",
            password = "thilen5",
        );
        val response = customerRepo.registerCustomer(Customer);
        val expectedResult: Boolean = true;
        val actualResult = response.success;
        Assert.assertEquals(expectedResult, actualResult);
    }

}