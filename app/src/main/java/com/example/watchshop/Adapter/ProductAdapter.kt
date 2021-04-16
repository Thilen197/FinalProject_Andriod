package com.example.watchshop.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.watchshop.R
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Product
import com.example.watchshop.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductAdapter (
        private val context: Context,
        private val lstProduct: ArrayList<Product>
    ): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {



        class ProductViewHolder(view:View):RecyclerView.ViewHolder(view){

            val tvname: TextView
            val tvmodel: TextView
            val tvdescription: TextView
            val tvprice: TextView
            val btnDelete:ImageButton
            val btnUpdate:ImageButton

            init {

                tvname=view.findViewById(R.id.tvname)
                tvprice=view.findViewById(R.id.tvprice)
                tvdescription=view.findViewById(R.id.tvdescription)
                tvmodel=view.findViewById(R.id.tvmodel)
                btnDelete = view.findViewById(R.id.btnDelete)
                btnUpdate = view.findViewById(R.id.btnUpdate)

            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewproductlayout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val product = lstProduct[position]
        holder.tvname.text = product.watch_name.toString()
        holder.tvmodel.text = product.model.toString()
        holder.tvprice.text = product.price.toString()
        holder.tvdescription.text = product.Description.toString()
        holder.btnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete product")
            builder.setMessage("Are you sure you want to delete ${product.watch_name} ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {

                    try {
                        val productRepository = ProductRepository()
                        val response = productRepository.deleteproduct(product?._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Product Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Dispatchers.Main) {
                            lstProduct.remove(product)
                            notifyDataSetChanged()
                        }
                    }catch (ex: Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,
                                ex.toString(),
                                Toast.LENGTH_SHORT)
                                .show()
                        }
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

        holder.btnUpdate.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return lstProduct.size
    }

}