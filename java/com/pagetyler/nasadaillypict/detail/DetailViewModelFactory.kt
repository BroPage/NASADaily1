package com.pagetyler.nasadaillypict.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pagetyler.nasadaillypict.network.NasaPicture

class DetailViewModelFactory (
    private val NasaPicture: NasaPicture,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(NasaPicture, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}