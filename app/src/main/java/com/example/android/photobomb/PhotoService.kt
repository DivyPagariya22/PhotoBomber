package com.example.android.photobomb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {

    companion object {
        //        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = "J9Koy-srWqVBAVj97-7Bw2j6Wd99sAKxZhX4Hom4pag"
    }

    @Headers("Accept-version: v1","Authorization: Client-ID $CLIENT_ID")
    @GET("photos")
    fun getPhotos(@Query("per_page")per_page : Int) : Call<List<Photo>>

    @Headers("Accept-version: v1","Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    fun searchPhoto(@Query("query")query: String,
                    @Query("per_page")per_page : Int) : Call<PhotoResults>
}