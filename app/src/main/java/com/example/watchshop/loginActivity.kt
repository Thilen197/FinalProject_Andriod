package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.watchshop.db.watchshopDB
import com.example.watchshop.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class loginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        tvRegister = findViewById(R.id.tvregister)
        tvRegister.setOnClickListener {
            startActivity(Intent(this@loginActivity, Register::class.java))
        }

        login.setOnClickListener {
            login()
        }
    }
    private fun login() {
        val username = username.text.toString()
        val password = password.text.toString()
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = watchshopDB
                    .getInstance(this@loginActivity)
                    .getUserDAO()
                    .checkUser(username, password)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@loginActivity,
                            "Invalid credentials",
                            Toast.LENGTH_SHORT
                    )
                            .show()
                }
            } else {
                startActivity(
                        Intent(
                                this@loginActivity,
                                MainActivity::class.java
                        )
                )
            }
        }
    }
}