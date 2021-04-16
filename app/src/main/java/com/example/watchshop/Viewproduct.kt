package com.example.watchshop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watchshop.entity.Product

class Viewproduct : AppCompatActivity() {
    private lateinit var ichome : ImageView
    private lateinit var iccart : ImageView
    private lateinit var icprofile : ImageView
    private lateinit var icwatch : ImageView

    private lateinit var tvname : TextView
    private lateinit var tvmodel : TextView
    private lateinit var tvprice : TextView
    private lateinit var tvdescription : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewproduct)


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



        class ProductAdapter(
                private val context: Context,
                private val lstProduct: ArrayList<Product>
        ) : RecyclerView.Adapter<ProductAdapter.StudentViewHolder>() {
            class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val tvname: TextView = view.findViewById(R.id.tvproductname)
                val tvprice: TextView = view.findViewById(R.id.tvprice)
                val ivproductpic: ImageView = view.findViewById(R.id.ivproductpic)
                val btnUpdate: ImageButton = view.findViewById(R.id.btnUpdate)
                val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.productveiw, parent, false)
                return StudentViewHolder(view)
            }
            override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
                val product = lstProduct[position]
                holder.tvproductname.text = product.productname
                holder.tvprice.text = product.price.toString()
                val productpic = ServiceBuilder.loadImagePath() + product.productpic
                Glide.with(context)
                        .load(productpic)
                        .into(holder.ivproductpic)
                holder.btnDelete.setOnClickListener {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Delete student")
                    builder.setMessage("Are you sure you want to delete ${product.productname} ??")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                    builder.setPositiveButton("Yes") { _, _ ->
                        CoroutineScope(Dispatchers.IO).launch {
                            Wmarketdb.WmInstance(context).getUserDao()
                                    .DeleteProduct(product)
                            withContext(Dispatchers.Main) {
                                notifyItemRemoved(position);
                                lstProduct.removeAt(position)
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    builder.setNegativeButton("No") { _, _ ->
                        Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
                    }
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
                holder.btnUpdate.setOnClickListener {
                    val intent = Intent(context, UpdateProductActivity::class.java);
                    intent.putExtra("data", product);
                    context.startActivity(intent);
                }
            }

            override fun getItemCount(): Int {
                return lstProduct.size
            }
        }

    }
}