package com.pagetyler.nasadaillypict.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pagetyler.nasadaillypict.getCurrentDate
import com.pagetyler.nasadaillypict.getSiteKey1
import com.pagetyler.nasadaillypict.network.NasaPicture
import com.pagetyler.nasadaillypict.network.NetworkApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.http.GET

enum class MarsApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {


    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<MarsApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _pictures = MutableLiveData<List<NasaPicture>>()
    val pictures: LiveData<List<NasaPicture>>
        get() = _pictures
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )


    private val _navigateToSelectedPicture = MutableLiveData<NasaPicture>()
    val navigateToSelectedPicture: LiveData<NasaPicture>
        get() = _navigateToSelectedPicture

    init {
        getNASAPictures()
    }

    @GET("https://api.nasa.gov/planetary/planetary/apod?api_key=DEMO_KEY")
    internal fun getNASAPictures() {

        coroutineScope.launch {

            try {
                var getPicturesDeferred = NetworkApi.RETROFIT_SERVICE.getPictures(getSiteKey1().toString(), getCurrentDate())

                var result = getPicturesDeferred.await()

                var listResult : List<NasaPicture> = listOf(result)

//                _status.value = "Success: ${listResult.size} Mars properties retrieved"
                _status.value = MarsApiStatus.DONE
                _pictures.value = listResult


            } catch (e: Exception) {
//                _status.value = "Failure: ${e.message}"
                _status.value = MarsApiStatus.ERROR
                _pictures.value = ArrayList()
                Log.e(this.javaClass.name, e.message ?: "**No message returned with error on [getPicturesDeferred] ")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPictureDetails(NasaPicture: NasaPicture) {
        _navigateToSelectedPicture.value = NasaPicture
    }

    fun displayPictureDetailsComplete() {
        _navigateToSelectedPicture.value = null
    }
    fun getPictures() {

        getNASAPictures()
    }

}



