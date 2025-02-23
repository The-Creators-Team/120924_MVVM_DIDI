package com.example.mvvmapplication.data.api

import com.example.mvvmapplication.data.model.DogImage
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogImage>

    @GET("breeds/image/random/10")
    suspend fun getRandomDogImages(): Response<DogImage>
}
