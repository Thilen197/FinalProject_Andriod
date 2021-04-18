package com.example.watchshop

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watchshop.entity.Product
import com.example.watchshop.repository.ProductRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProductActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var etname : EditText
    private lateinit var etprice : EditText
    private lateinit var etmodel : EditText
    private lateinit var etdescription : EditText
    private lateinit var btnupdateproduct:Button
    var id=""
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        supportActionBar?.setTitle("Update Profile")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        etname = findViewById(R.id.etname)
        etprice = findViewById(R.id.etprice)
        etmodel = findViewById(R.id.etmodel)
        etdescription = findViewById(R.id.etdescription)
        btnupdateproduct= findViewById(R.id.btnupdateproduct)

        btnupdateproduct.setOnClickListener{
            updateproduct()
        }
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager;
        if (!checkSensor()) {
            return
        } else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener(
                    this@UpdateProductActivity,
                    sensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        }

        val intent = intent
        if(intent.extras!=null){
            val productid = intent.getStringExtra("id")
            val watch_name = intent.getStringExtra("watch_name");
            val price=intent.getStringExtra("price")
            val model=intent.getStringExtra("model")
            val Description=intent.getStringExtra("Description")
            etname.setText("$watch_name")
            etprice.setText("$price")
            etmodel.setText("$model")
            etdescription.setText("$Description")
            id="$productid"
        }

    }

    private fun updateproduct() {

        val watch_name=etname.text.toString()
        val price=etprice.text.toString()
        val model=etmodel.text.toString()
        val description=etdescription.text.toString()
        val product = Product(
            watch_name=watch_name,
            price = price,
            model = model,
            Description = description

        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.updateproduct(id,product)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {

                        Toast.makeText(this@UpdateProductActivity, "updated", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@UpdateProductActivity,Viewproduct::class.java))
                    }
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@UpdateProductActivity, "failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            catch (e:Exception){
                print(e)
            }
        }
        }


    private fun checkSensor(): Boolean {
        var flag = true;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag;
    }
    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0];
        if (values <= 4) {
            Toast.makeText(this, "You blocked the screen.", Toast.LENGTH_SHORT).show()
        }
    }
    override  fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    private fun vibratesensor() {
        val vibrator = this?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(
                        VibrationEffect.createOneShot(
                                2000,
                                VibrationEffect.DEFAULT_AMPLITUDE
                        )
                )
            }
        }else{
            vibrator.vibrate(2000)
        }
    }
}


