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
    private lateinit var btnmyprofile: Button
    private lateinit var btnfeedback: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)



        btnwatch = findViewById(R.id.btnwatch)
        mycart = findViewById(R.id.mycart)
        btnmyprofile = findViewById(R.id.btnmyprofile)
        btnfeedback = findViewById(R.id.btnfeedback)

        btnwatch.setOnClickListener {
            val intent = Intent(this, Viewproduct::class.java)
            startActivity(intent)
        }
        mycart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }
        btnmyprofile.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }
        btnfeedback.setOnClickListener {
            val intent = Intent(this, addproduct::class.java)
            startActivity(intent)
        }

    }

}
