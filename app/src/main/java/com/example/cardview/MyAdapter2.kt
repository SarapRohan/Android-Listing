package com.example.cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_package.view.*

class MyAdapter2(private val packages: ArrayList<CarPackage>):
    RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {

    class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_package, parent, false)
        return  MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.price.text = packages[position].price
        holder.itemView.description.text = packages[position].deliveryOption
        holder.itemView.priceAfter.text = packages[position].priceAfter
    }

    override fun getItemCount(): Int {
        return packages.size
    }

}