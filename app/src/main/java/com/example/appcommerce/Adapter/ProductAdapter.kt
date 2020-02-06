package com.example.appcommerce.Adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcommerce.R
import com.example.appcommerce.model.Product

class ProductAdapter (val list: List<Product>, val context: Context) : RecyclerView.Adapter<>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.iv_category_icon)
        val title: TextView = itemView.findViewById(R.id.tv_category_title)
    }

}