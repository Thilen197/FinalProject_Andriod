package com.example.watchshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.watchshop.db.watchshopDB
import com.example.watchshop.entity.User

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
                    val username = etname.text.toString()
                    val contact = etphone.text.toString()
                    val email = etusername.text.toString()
                    val password = etPassword.text.toString()
                    val confirmPassword = etConpassword.text.toString()
                    if (password != confirmPassword) {
                        etPassword.error = "Password does not match"
                        etPassword.requestFocus()
                        return@setOnClickListener
                    }
                    else {
                        val user = User(username, phone, email, password)
                        CoroutineScope(Dispatchers.IO).launch {
                            watchshopDB.getInstance(this@Register).getUserDAO()
                                    .registerUser(user)
                            withContext(Main) {​​​​​​​​
                                startActivity(Intent(this@RegisterActivity, loginActivity::class.java));
                                Toast.makeText(this@RegisterActivity, "User Registered", Toast.LENGTH_SHORT)
                                        .show();
                            }​​​​​​​​
                        }​​​​​​​​
                    }​​​​​​​​
                }​​​​​​​​
            }​​​​​​​​
        }​​​​​​​​

        ​[14:36] Aatish Raj Shrestha


        class SplashActivity : AppCompatActivity() {​​​​​​​​
            override fun onCreate(savedInstanceState: Bundle?) {​​​​​​​​
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_splash)
                CoroutineScope(Dispatchers.Main).launch {​​​​​​​​
                    delay(1000)
                    startActivity(Intent(this@SplashActivity, loginActivity::class.java))
                    finish()
                    getSharedPref()
                }​​​​​​​​
            }​​​​​​​​
            private fun getSharedPref() {​​​​​​​​
                val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
                val username = sharedPref.getString("username", "")
                val password = sharedPref.getString("password", "")
                Toast.makeText(this, "Username : $username and password : $password", Toast.LENGTH_SHORT)
                        .show()
            }​​​​​​​​
        }​​​​​​​​


    }
}