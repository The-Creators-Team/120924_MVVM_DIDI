package com.example.mvvmapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapplication.databinding.ActivityMainBinding
import com.example.mvvmapplication.ui.adapter.DogImagesAdapter
import com.example.mvvmapplication.ui.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dogViewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dogViewModel = ViewModelProvider(this).get(DogViewModel::class.java)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        dogViewModel.dogImages.observe(this, Observer { dogImageUrls ->
            if (dogImageUrls != null) {
                val adapter = DogImagesAdapter(dogImageUrls)
                recyclerView.adapter = adapter
            }
        })

        dogViewModel.errorMessage.observe(this, Observer { error ->

            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })


        dogViewModel.fetchRandomDogImages()
    }
}


