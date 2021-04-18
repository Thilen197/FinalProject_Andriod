package com.example.watchshop

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
    private lateinit var btncustomerRegistration: View
    private lateinit var linearlayout:LinearLayout


    companion object {
        var customerid: String = "";
        var customerusername: String = "";
        var customeremail: String = "";
        var customerphone: String = "";

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setTitle("Login")

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        linearlayout = findViewById(R.id.linearlayout)
        btncustomerRegistration = findViewById(R.id.btncustomerRegistration)
        btncustomerRegistration.setOnClickListener {
            startActivity(Intent(this@loginActivity, Register::class.java))
        }



        login.setOnClickListener {
            if (validateInput()) {
                login()
                showHighPriorityNotification()
            }

            }
        }


    private fun validateInput(): Boolean {
        if (username.text.toString() == "") {
            username.error = "Please Enter Username"
            return false
        }
        if (password.text.toString() == "") {
            if (password.text.toString() == "") {
                password.error = "Please Enter Password"
                return false
            }

        }
        return true

    }

    private fun showHighPriorityNotification() {
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannel(this)
        notificationChannels.createNotificationChannels()
        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Notification")
                .setContentText("You're logged in successfully ")
                .setColor(Color.BLUE)
                .build()
        notificationManager.notify(1, notification)
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

                    customerid = response.data?._id.toString();
                    customerusername=response.data?.username.toString();
                    customeremail = response.data?.email.toString();
                    customerphone= response.data?.mobile.toString();


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


