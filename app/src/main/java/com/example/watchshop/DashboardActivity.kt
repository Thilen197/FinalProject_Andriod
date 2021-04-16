package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnwatch: Button
    private lateinit var mycart: ImageView
    private lateinit var btnprofile: Button
    private lateinit var btnaddproduct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        btnwatch = findViewById(R.id.btnwatch)
        mycart = findViewById(R.id.mycart)
        btnprofile = findViewById(R.id.btnprofile)
        btnaddproduct = findViewById(R.id.btnaddproduct)

        btnwatch.setOnClickListener {
            val intent = Intent(this, addproduct::class.java)
            startActivity(intent)
        }
//        mycart.setOnClickListener {
//            val intent = Intent(this, Cart::class.java)
//            startActivity(intent)
//        }
        btnprofile.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }
        btnaddproduct.setOnClickListener {
            val intent = Intent(this, addproduct::class.java)
            startActivity(intent)
        }

    }
}
