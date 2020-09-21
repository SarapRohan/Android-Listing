package com.example.cardview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import kotlinx.android.synthetic.main.card.view.*


class MyAdapter(private val carsList: ArrayList<Car>):
   RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        val context: Context = view.context
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recyclePackages)
        lateinit var viewAdapter: RecyclerView.Adapter<*>

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card,parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.carName.text = carsList[position].name
        holder.itemView.carDescription.text = carsList[position].description
        holder.itemView.carPrice.text = carsList[position].price

        Glide.with(holder.itemView)
            .load(carsList[position].imgUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.itemView.imageView)

        holder.viewAdapter = MyAdapter2(carsList[position].carPackages)
        holder.recyclerView.layoutManager = LinearLayoutManager(holder.context)
        holder.recyclerView.adapter = holder.viewAdapter

        holder.itemView.recyclePackages.visibility = View.GONE

        holder.itemView.setOnClickListener(View.OnClickListener {
            if (holder.itemView.recyclePackages.visibility == View.VISIBLE) {
                holder.itemView.recyclePackages.visibility = View.GONE
            } else {
                holder.itemView.recyclePackages.visibility = View.VISIBLE
            }
        })



    }

    override fun getItemCount(): Int {
        return carsList.size
    }

}

