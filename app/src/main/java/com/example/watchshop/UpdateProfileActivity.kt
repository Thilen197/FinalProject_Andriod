package com.example.watchshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watchshop.entity.Customer
import com.example.watchshop.repository.CustomerRepository
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
        etusername = findViewById(R.id.etusername)
        etemail = findViewById(R.id.etemail)
        etphone = findViewById(R.id.etphone)
        btnupdateprofile = findViewById(R.id.btnupdateprofile)

        loadConsumerData();
        btnupdateprofile.setOnClickListener {
            updateProfile()
        }

        val intent = intent
        if(intent.extras!=null){
            val productid = intent.getStringExtra("id")
            val username = intent.getStringExtra("username");
            val email=intent.getStringExtra("email")
            val mobile=intent.getStringExtra("phone")
            etusername.setText("$username")
            etemail.setText("$email")
            etphone.setText("$mobile")
            id="$productid"
        }

    }
    private fun loadConsumerData() {
        CoroutineScope(Dispatchers.IO).launch {
            val consumerRepo = CustomerRepository();
            val response = consumerRepo.viewprofile();
            if (response.success == true) {
                withContext(Dispatchers.Main) {
                    val data = response.data;
                    if (data != null) {
                        etusername.setText(data.username.toString());
                        etemail.setText(data.email.toString());
                        etphone.setText(data.mobile.toString());


                    }
                }

        }
    }

    }
    private fun updateProfile() {
        var username = etusername.text.toString()
        var email = etemail.text.toString()
        var phone = etphone.text.toString()

        val Customer = Customer(
                username = username,
                email = email,
                mobile = phone
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val CustomerRepository= CustomerRepository()
                val response = CustomerRepository.Updateprofile(Customer)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {

                        Toast.makeText(this@UpdateProfileActivity, "updated", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@UpdateProfileActivity,Viewproduct::class.java))
                    }
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@UpdateProfileActivity, "failed", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
            catch (e:Exception){
                print(e)
            }
        }
    }
    }



