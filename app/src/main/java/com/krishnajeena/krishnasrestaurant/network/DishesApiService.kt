package com.krishnajeena.krishnasrestaurant.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.krishnajeena.krishnasrestaurant.data.Dish
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DishesApiService{

    @GET("menu.json")
    suspend fun getDishes():List<Dish>

    @GET("menu2")
    suspend fun getDishesLunch():List<Dish>

    @GET("menu3")
    suspend fun getDishesDinner():List<Dish>

}
