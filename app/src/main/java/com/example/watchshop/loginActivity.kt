package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.watchshop.R
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.repository.CustomerRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class loginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var btncustomerRegistration: Button
    private lateinit var linearlayout:LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        linearlayout = findViewById(R.id.linearlayout)
        btncustomerRegistration = findViewById(R.id.btncustomerRegistration)
        btncustomerRegistration.setOnClickListener {
            startActivity(Intent(this@loginActivity, Register::class.java))
        }



        login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = username.text.toString()
        val password = password.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = CustomerRepository()
                val response = repository.checkCustomer(username, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.Token
                    startActivity(
                            Intent(
                                    this@loginActivity,

                                    DashboardActivity::class.java
                            )
                    )
                    finish()
                }
                else {
                    withContext(Dispatchers.Main) {
                        val snack =
                                Snackbar.make(
                                        linearlayout,
                                        "Invalid credentials",
                                        Snackbar.LENGTH_LONG
                                )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }
            }catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@loginActivity,
                            ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}


