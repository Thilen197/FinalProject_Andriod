package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class addproduct : AppCompatActivity() {
    private lateinit var image : ImageView
    private lateinit var etname :EditText
    private lateinit var etprice : EditText
    private lateinit var etmodel : EditText
    private lateinit var etdescription : EditText
    private lateinit var btnadd : Button

    private lateinit var ichome : ImageView
    private lateinit var iccart : ImageView
    private lateinit var icprofile : ImageView
    private lateinit var icwatch : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)


        image= findViewById(R.id.image)
        etname = findViewById(R.id.etname)
        etprice = findViewById(R.id.etprice)
        etmodel = findViewById(R.id.etmodel)
        etdescription = findViewById(R.id.etdescription)
        btnadd = findViewById(R.id.btnadd)













//        binding icon

        icwatch = findViewById(R.id.icwatch)
        iccart = findViewById(R.id.iccart)
        icprofile = findViewById(R.id.icprofile)
        ichome = findViewById(R.id.ichome)

        icwatch.setOnClickListener {
            val intent = Intent(this, Viewproduct::class.java)
            startActivity(intent)
        }
        iccart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }
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
