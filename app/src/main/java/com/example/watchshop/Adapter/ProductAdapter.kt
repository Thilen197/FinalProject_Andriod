package com.example.watchshop.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.watchshop.R
import com.example.watchshop.api.ServiceBuilder
import com.example.watchshop.entity.Product
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

            init {

                tvname=view.findViewById(R.id.tvname)
                tvprice=view.findViewById(R.id.tvprice)
                tvdescription=view.findViewById(R.id.tvdescription)
                tvmodel=view.findViewById(R.id.tvmodel)

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
    }

    override fun getItemCount(): Int {
        return lstProduct.size
    }

}