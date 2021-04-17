package WearOS

import WearOS.api.ServiceBuilder
import WearOS.repository.CustomerRepository
import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watchshopwearos.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WearLoginActivity : WearableActivity() {

    private lateinit var wusername: EditText
    private lateinit var wpassword: EditText
    private lateinit var wlogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wear_login)
        wusername = findViewById(R.id.wusername)
        wpassword = findViewById(R.id.wpassword)
        wlogin = findViewById(R.id.wlogin)
        wlogin.setOnClickListener {
            login()
        }
    }
    private fun login() {
        val wusername = wusername.text.toString()
        val wpassword = wpassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = CustomerRepository()
                val response = repository.checkCustomer(wusername, wpassword)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.Token
                    startActivity(
                        Intent(
                            this@WearLoginActivity,
                            DashboardActivity::class.java
                        )
                    )
                    finish()
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@WearLoginActivity, "Login sucessfull", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@WearLoginActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        setAmbientEnabled()
    }
}