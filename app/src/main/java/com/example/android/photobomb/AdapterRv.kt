package com.example.android.photobomb

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop


class AdapterRV(private val context: Context, private val list: List<Photo>) : RecyclerView.Adapter<AdapterRV.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item,
                        parent,
                        false
                )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.textView.text = list[position].user.name
        val url = list[position].urls.small

        Glide.with(holder.imageView.getContext())
                .load(url)
                .centerCrop()
                .into(holder.imageView);
    }


    override fun getItemCount(): Int {
        return list.size
    }
}