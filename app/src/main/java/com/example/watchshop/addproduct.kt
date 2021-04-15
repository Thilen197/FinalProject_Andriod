package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.watchshop.entity.Product
import com.example.watchshop.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class addproduct : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var etname: EditText
    private lateinit var etprice: EditText
    private lateinit var etmodel: EditText
    private lateinit var etdescription: EditText
    private lateinit var btnadd: Button

    private lateinit var ichome: ImageView
    private lateinit var iccart: ImageView
    private lateinit var icprofile: ImageView
    private lateinit var icwatch: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)



        etname = findViewById(R.id.etname)
        etprice = findViewById(R.id.etprice)
        etmodel = findViewById(R.id.etmodel)
        etdescription = findViewById(R.id.etdescription)
        btnadd = findViewById(R.id.btnadd)
        image = findViewById(R.id.image)

        image.setOnClickListener {
            loadPopUpMenu()
        }

        btnadd.setOnClickListener {
            val Name = etname.text.toString()
            val model = etmodel.text.toString()
            val price = etprice.text.toString()
            val description = etdescription.text.toString()
            val watch_image =

            val product = Product(
                watch_name = Name, model = model,
                price = price, Description = description, watch_image =
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val productRepository = ProductRepository()
                    val response = productRepository.addProduct(product)
                    if (response.success == true) {
                        if (imageUrl != null) {
                            uploadImage(response.data!!._id!!)
                        }
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@Viewproduct,
                                "Book Ordered",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        startActivity(Intent(this@Viewproduct, ViewProduct::class.java));
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ProductActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }


}
}
}





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
