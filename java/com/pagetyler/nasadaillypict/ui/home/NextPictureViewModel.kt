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

class NextPictureViewModel : ViewModel() {



    private val _nxtPictures = MutableLiveData<List<NasaPicture>>()
    val nxtPictures: LiveData<List<NasaPicture>>
        get() = _nxtPictures
    private var nxtViewModelJob = Job()

    private val nxtCoroutineScope = CoroutineScope(
        nxtViewModelJob + Dispatchers.Main )


    private val _navigateToNextPicture = MutableLiveData<NasaPicture>()
    val navigateToNextPicture: LiveData<NasaPicture>
        get() = _navigateToNextPicture

    init {
        getNextPictures()
    }

    @GET("https://api.nasa.gov/planetary/planetary/apod?api_key=DEMO_KEY")
    internal fun getNextPictures() {

        nxtCoroutineScope.launch {

            try {
                var getPicturesDeferred = NetworkApi.RETROFIT_SERVICE.getPictures(getSiteKey1().toString(), getCurrentDate())

                var result = getPicturesDeferred.await()

                var listResult : List<NasaPicture> = listOf(result)

                _nxtPictures.value = listResult


            } catch (e: Exception) {
                _nxtPictures.value = ArrayList()
                Log.e(this.javaClass.name, e.message ?: "**No message returned with error on [getPicturesDeferred] ")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        nxtViewModelJob.cancel()
    }

    fun displayNextPictureDetails(NasaPicture: NasaPicture) {
        _navigateToNextPicture.value = NasaPicture
    }

    fun displayNextPictureDetailsComplete() {
        _navigateToNextPicture.value = null
    }
    fun getNext() {

        getNextPictures()
    }

}