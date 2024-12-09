package com.example.mvvmapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapplication.model.DogImage
import com.example.mvvmapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor (private val apiService: ApiService) : ViewModel() {

    val dogImages: MutableLiveData<List<String>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun fetchRandomDogImages() {
        viewModelScope.launch {
            try {
                val response: Response<DogImage> = apiService.getRandomDogImages()
                if (response.isSuccessful) {
                    val dogImageResponse = response.body()
                    dogImageResponse?.let {
                        dogImages.postValue(it.message)
                    }
                } else {
                    errorMessage.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Exception: ${e.message}")
            }
        }
    }
}

