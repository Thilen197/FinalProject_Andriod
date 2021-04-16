package com.example.watchshop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchshop.Adapter.ProductAdapter
import com.example.watchshop.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Viewproduct : AppCompatActivity() {
    private lateinit var ichome : ImageView
    private lateinit var iccart : ImageView
    private lateinit var icprofile : ImageView
    private lateinit var icwatch : ImageView


    private lateinit var recyclerView : RecyclerView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewproduct)
        icwatch = findViewById(R.id.icwatch)
        iccart = findViewById(R.id.iccart)
        icprofile = findViewById(R.id.icprofile)
        ichome = findViewById(R.id.ichome)
        recyclerView = findViewById(R.id.recyclerveiw)


        val adapter = ProductAdapter(this, arrayListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        fetchProducts()
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

    private fun fetchProducts() {
        CoroutineScope(Dispatchers.IO).launch {
//            val lstProduct =
//                    watchshopDB.WmInstance(this@Viewproduct)
//                            .getUserDao().viewProduct()


//            withContext(Dispatchers.Main){
//                recyclerView.adapter = ProductAdapter(this@Viewproduct,lstProduct as ArrayList<Product>)
//                recyclerView.layoutManager = LinearLayoutManager(this@Viewproduct)

            }
        }
    }
