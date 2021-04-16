package com.example.watchshop

import com.example.watchshop.repository.CustomerRepository
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WatchshopTest {
    private lateinit var customer: CustomerRepository;
    @Test
    fun loginTest() = runBlocking {
        customer = CustomerRepository();
        val response = customer.checkCustomer("thilen2021","thilen2021")
        val expectedResult: Boolean = true;
        val actualResult = response.success;
        Assert.assertEquals(expectedResult, actualResult);
    }

}