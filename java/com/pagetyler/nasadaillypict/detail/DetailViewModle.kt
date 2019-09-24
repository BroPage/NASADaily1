package com.pagetyler.nasadaillypict.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pagetyler.nasadaillypict.network.NasaApiFilter
import com.pagetyler.nasadaillypict.network.NasaPicture
import com.pagetyler.nasadaillypict.network.NetworkApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class NasaApiStatus { LOADING, ERROR, DONE }
class DetailViewModel( NasaPicture: NasaPicture, app: Application) : AndroidViewModel(app) {
    private val _selectedPicture = MutableLiveData<NasaPicture>()
    val selectedPicture: LiveData<NasaPicture>
        get() = _selectedPicture

    init {
        _selectedPicture.value = NasaPicture
    }
}

 /*   // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<NasaApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<NasaApiStatus>
        get() = _status

    private val _selectedPicture = MutableLiveData<List<NasaPicture>>()
    val selectedPicture: LiveData<List<NasaPicture>>
        get() = _selectedPicture

    private val _navigateToSelectedPicture = MutableLiveData<NasaPicture>()
    val navigateToSelectedProperty: LiveData<NasaPicture>
        get() = _navigateToSelectedPicture

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )
    private var BASE_URL = ""

    *//**
     * Call getNasPictures() on init so we can display status immediately.
     *//*
    init {
        getNasaPictures(NasaApiFilter.SHOW_ALL)
    }

    private fun getNasaPictures(filter: NasaApiFilter) {
        coroutineScope.launch {

            try {
                var getPicturesDeferred = NetworkApi.RETROFIT_SERVICE.getPictures(filter.value)

                var result = getPicturesDeferred.await()
                var listResult = arrayListOf(result)
                //_status.value = "Success: ${listResult.size} Mars pictures retrieved"
                _status.value = NasaApiStatus.DONE

                if (listResult.size > 0) {
                    _selectedPicture.value = listResult
                }

            } catch (e: Exception) {
                //_status.value = "Failure: ${e.message}"
                _status.value = NasaApiStatus.ERROR
                _selectedPicture.value = ArrayList()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
       fun updateFilter(filter: NasaApiFilter) {
        getNasaPictures(filter)
    }

    fun displayPropertyDetails(NasaPicture: NasaPicture) {
        _navigateToSelectedPicture.value = NasaPicture
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPicture.value = null
    }
}*/