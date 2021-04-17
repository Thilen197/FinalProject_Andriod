package com.example.watchshop

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.PopupMenu
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Product
import com.example.watchshop.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

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

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

    private var productId: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        supportActionBar?.setTitle("Add Product")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        icwatch = findViewById(R.id.icwatch)
        iccart = findViewById(R.id.iccart)
        icprofile = findViewById(R.id.icprofile)
        ichome = findViewById(R.id.ichome)

        etname = findViewById(R.id.etname)
        etprice = findViewById(R.id.etprice)
        etmodel = findViewById(R.id.etmodel)
        etdescription = findViewById(R.id.etdescription)
        btnadd = findViewById(R.id.btnadd)
        image = findViewById(R.id.image)

        // Setting Click Listioner

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
//
        image.setOnClickListener {
            loadPopUpMenu()
        }

        btnadd.setOnClickListener {
            val Name = etname.text.toString()
            val model = etmodel.text.toString()
            val price = etprice.text.toString()
            val description = etdescription.text.toString()
//            val image = image.toString()

            val product = Product(
                    watch_name = Name, model = model,
                    price = price, Description = description
//                    , watch_image = watch_image
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val productRepository = ProductRepository()
                    val response = productRepository.addProduct(product)



                    if (response.success == true) {
                        if (imageUrl != null) {
                            uploadImage(response.data?._id.toString())
                        }
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    this@addproduct,
                                    "${response.data?._id}",
                                    Toast.LENGTH_LONG
                            ).show()
                            setResult(RESULT_OK)
//                            finish()

                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        startActivity(Intent(this@addproduct, Viewproduct::class.java))
                        Toast.makeText(
                                this@addproduct,
                                "$ex",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    private fun uploadImage(id:String) {
        println(ServiceBuilder.token)
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                    MultipartBody.Part.createFormData("watch_image", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val productRepository = ProductRepository()
                    val response = productRepository.addProductImage(id,body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@addproduct, "Uploaded", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Mero Error ", ex.localizedMessage)
                        Toast.makeText(
                                this@addproduct,
                                ex.localizedMessage,
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(this@addproduct, image)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                R.id.menuGallery ->
                    openGallery()
            }
            true
        }
        popupMenu.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                image.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpeg")
                imageUrl = file!!.absolutePath
                image.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }
    private fun bitmapToFile(
            bitmap: Bitmap,
            fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

}


