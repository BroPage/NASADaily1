package com.pagetyler.nasadaillypict.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pagetyler.nasadaillypict.*
import com.pagetyler.nasadaillypict.network.baseURL
import com.squareup.moshi.Moshi


import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.http.Query

enum class NasaApiFilter(val value: String) {
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all") }

//  Base URL definition (Not to cool being hard coded)
internal  var baseURL =  "https://api.nasa.gov/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

private var retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseURL)
    .build()


private var newRetrofit : Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseURL)
    .build()


fun changeApiBaseUrl(newApiBaseUrl: String) {
    baseURL = newApiBaseUrl
    retrofit  =   newRetrofit
}

/**
 * A public interface that exposes the [getPictures] method
 */
var endPoint : String = getSiteURL1() + getSiteKey1()


interface NetworkApiService {

    @GET("planetary/apod")
    fun getPictures(@Query("api_key") DEMO_KEY: String, @Query("date") DATE: String):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<NasaPicture>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object NetworkApi {

    val RETROFIT_SERVICE : NetworkApiService by lazy { retrofit.create(NetworkApiService::class.java) }
}
