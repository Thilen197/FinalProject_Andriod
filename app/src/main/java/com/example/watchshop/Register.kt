package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.watchshop.entity.Customer
import com.example.watchshop.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Register : AppCompatActivity() {
    private lateinit var etemail: EditText
    private lateinit var etphone: EditText
    private lateinit var etusername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConpassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        etemail = findViewById(R.id.etemail)
        etphone = findViewById(R.id.etphone)
        etusername = findViewById(R.id.etusername)
        etPassword = findViewById(R.id.etpassword)
        etConpassword = findViewById(R.id.etconpassword)
        btnRegister = findViewById(R.id.btnregister)

        btnRegister.setOnClickListener {

            val mobile = etphone.text.toString()
            val username = etusername.text.toString()
            val email = etemail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConpassword.text.toString()



            if (password != confirmPassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            } else {
                val customer = Customer(
                    username = username,
                    email = email,
                    mobile = mobile,
                    password = password
                )

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val userRepository = CustomerRepository()
                        val response = userRepository.registerCustomer(customer)
                        if (response.success == true) {
                            withContext(Dispatchers.Main)

                            {
                                startActivity(Intent(this@Register,loginActivity::class.java));
                                Toast.makeText(
                                    this@Register,
                                    "Register Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Main) {
                            Toast.makeText(
                                this@Register,
                                "User ALready Exist",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }
    }
}