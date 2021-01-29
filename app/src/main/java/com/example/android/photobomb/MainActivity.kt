package com.example.android.photobomb

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.photobomb.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//https://api.unsplash.com/photos/?client_id=J9Koy-srWqVBAVj97-7Bw2j6Wd99sAKxZhX4Hom4pag
private lateinit var binding: ActivityMainBinding
private const val BASE_URL = "https://api.unsplash.com/"
private const val TAG = "MainActivity"
private lateinit var AllData: List<Photo>
private lateinit var adapterRV: AdapterRV
private lateinit var photoService: PhotoService
private lateinit var searchText: String

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        //Instance of Photo Service
        photoService = retrofit.create(PhotoService::class.java)

        //Fetching photos
        photoService.getPhotos(30).enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                Log.i(TAG, "onResponce $response")
                val photosData = response.body()
                if (photosData == null) {
                    Log.w(TAG, "did not recive valid responce body ")
                    return
                }
                AllData = photosData
                updatePhotos(AllData)
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e(TAG, "on Failure $t")
            }

        })
    }



    fun updatePhotos(allData: List<Photo>) {
        adapterRV = AdapterRV(this, allData)
        binding.recyclerView.adapter = adapterRV
    }

    fun Search(view: View) {
        searchText = binding.searchText.getText().toString()
        photoService.searchPhoto(searchText , 30).enqueue(object : Callback<PhotoResults> {

            override fun onResponse(call: Call<PhotoResults>, response: Response<PhotoResults>) {
                val photoData =  response.body()
                if (photoData == null) {
                    Log.w(TAG, "did not recive valid responce body ")
                    return
                }
                val listOfphoto = photoData.results
                updatePhotos(listOfphoto)

            }

            override fun onFailure(call: Call<PhotoResults>, t: Throwable) {
                Log.e(TAG, "on Failure $t")
            }

        })
    }
}