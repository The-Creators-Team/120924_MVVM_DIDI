package com.example.mvvmapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmapplication.databinding.ItemDogImageBinding

class DogImagesAdapter(private val dogImageUrls: List<String>) :
    RecyclerView.Adapter<DogImagesAdapter.DogImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        val binding = ItemDogImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        val imageUrl = dogImageUrls[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int {
        return dogImageUrls.size
    }

    class DogImageViewHolder(private val binding: ItemDogImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.dogImageView)
        }
    }
}


