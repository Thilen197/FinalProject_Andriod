package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watchshop.db.watchshopDB
import com.example.watchshop.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Register : AppCompatActivity() {
    private lateinit var etname: EditText
    private lateinit var etphone: EditText
    private lateinit var etusername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConpassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


                etname = findViewById(R.id.etname)
                etphone = findViewById(R.id.etphone)
                etusername = findViewById(R.id.etusername)
                etPassword = findViewById(R.id.etpassword)
                etConpassword = findViewById(R.id.etconpassword)
                btnRegister = findViewById(R.id.btnregister)

                btnRegister.setOnClickListener {

                    val phone = etphone.text.toString()
                    val username = etusername.text.toString()
                    val password = etPassword.text.toString()
                    val confirmPassword = etConpassword.text.toString()
                    if (password != confirmPassword) {
                        etPassword.error = "Password does not match"
                        etPassword.requestFocus()
                        return@setOnClickListener
                    }
                    else {
                        val user = User( username, password, phone)
                        CoroutineScope(Dispatchers.IO).launch {
                            watchshopDB.getInstance(this@Register).getUserDAO()
                                .registerUser(user)
                            withContext(Main) {
                                startActivity(Intent(this@Register, loginActivity::class.java));
                                Toast.makeText(this@Register, "User Registered", Toast.LENGTH_SHORT)
                                    .show();
                            }
                        }
                    }
                }

    }
}