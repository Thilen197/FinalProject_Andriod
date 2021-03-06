package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class profile : AppCompatActivity() {
    private lateinit var ichome: ImageView
    private lateinit var iccart: ImageView
    private lateinit var icprofile: ImageView
    private lateinit var icwatch: ImageView

    private lateinit var tvcustomerusername: TextView
    private lateinit var tvcustomeremail: TextView
    private lateinit var tvcustomerphone: TextView
    private lateinit var tvprofile: TextView
    private lateinit var logout: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.setTitle("Profile")

        tvprofile = findViewById(R.id.tvprofile);

        tvcustomerusername = findViewById(R.id.tvcustomerusername);
        tvcustomerusername.text = loginActivity.customerusername.toString();

        tvcustomeremail = findViewById(R.id.tvcustomeremail);
        tvcustomeremail.text = loginActivity.customeremail.toString();

        tvcustomerphone = findViewById(R.id.tvcustomerphone);
        tvcustomerphone.text = loginActivity.customerphone.toString();

        icwatch = findViewById(R.id.icwatch)
        iccart = findViewById(R.id.iccart)
        icprofile = findViewById(R.id.icprofile)
        ichome = findViewById(R.id.ichome)
        logout = findViewById(R.id.logout)


        logout.setOnClickListener {
            logout()
        }


        tvprofile.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            startActivity(intent)
        }


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

    fun logout() {
        getSharedPreferences("MyPref", MODE_PRIVATE)?.edit()?.clear()?.apply()
        val intent = Intent(this, loginActivity::class.java)
        startActivity(intent)
        Toast.makeText(
                this,
                "Logout Successfully", Toast.LENGTH_SHORT
        ).show()
    }
}
