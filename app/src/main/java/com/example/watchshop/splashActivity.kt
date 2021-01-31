package com.example.watchshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class splashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, loginActivity::class.java))
            finish()
            getSharedPref()
        }
    }
    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "")
        val password = sharedPref.getString("password", "")
        Toast.makeText(this, "Username : $username and password : $password", Toast.LENGTH_SHORT)
            .show()
    }
}
