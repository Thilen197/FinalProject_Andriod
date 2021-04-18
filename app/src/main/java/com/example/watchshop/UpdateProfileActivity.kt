package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watchshop.entity.Customer
import com.example.watchshop.entity.Product
import com.example.watchshop.repository.CustomerRepository
import com.example.watchshop.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var etusername: EditText
    private lateinit var etemail: EditText
    private lateinit var etphone: EditText
    private lateinit var btnupdateprofile: Button
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        etusername = findViewById(R.id.etname)
        etemail = findViewById(R.id.etprice)
        etphone = findViewById(R.id.etmodel)
        btnupdateprofile = findViewById(R.id.btnupdateproduct)
        btnupdateprofile.setOnClickListener {
            updateprofile()
        }

        val intent = intent
        if (intent.extras != null) {
            val productid = intent.getStringExtra("id")
            val pusername = intent.getStringExtra("username");
            val pemail = intent.getStringExtra("email")
            val pmobile = intent.getStringExtra("mobile")

            etusername.setText("$pusername")
            etemail.setText("$pemail")
            etphone.setText("$pmobile")
            id = "$productid"
        }
    }


    private fun updateprofile() {
        val username = etusername.text.toString()
        val email = etemail.text.toString()
        val phone = etphone.text.toString()

        val customer = Customer(
                username = username,
                email = email, mobile = phone

        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = CustomerRepository()
                val response = productRepository.viewprofile(id, customer)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {

                        Toast.makeText(this@UpdateProfileActivity, "updated", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@UpdateProfileActivity, profile::class.java))
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@UpdateProfileActivity, "failed", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            } catch (e: Exception) {
                print(e)
            }
        }

    }

}
