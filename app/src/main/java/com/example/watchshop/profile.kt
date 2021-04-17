package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class profile : AppCompatActivity() {
    private lateinit var ichome : ImageView
    private lateinit var iccart : ImageView
    private lateinit var icprofile : ImageView
    private lateinit var icwatch : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.setTitle("Profile")

        icwatch = findViewById(R.id.icwatch)
        iccart = findViewById(R.id.iccart)
        icprofile = findViewById(R.id.icprofile)
        ichome = findViewById(R.id.ichome)

//        icwatch.setOnClickListener {
//            val intent = Intent(this, Viewproduct::class.java)
//            startActivity(intent)
//        }
//        iccart.setOnClickListener {
//            val intent = Intent(this, Cart::class.java)
//            startActivity(intent)
//        }
        icprofile.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }
        ichome.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }
}