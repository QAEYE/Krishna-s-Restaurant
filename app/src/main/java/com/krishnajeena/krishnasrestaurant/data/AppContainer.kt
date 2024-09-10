package com.krishnajeena.krishnasrestaurant.data

import com.krishnajeena.krishnasrestaurant.network.DishesApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val krishnaDataRespository : KrishnaDataRespository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://raw.githubusercontent.com/QAEYE/Data-For-K-SRest/main/"


    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: DishesApiService by lazy {
        retrofit.create(DishesApiService::class.java)
    }
    override val krishnaDataRespository: KrishnaDataRespository by lazy {
        NetworkKrishnaDataRepository(retrofitService)
    }


    /**
     * DI implementation for Mars photos repository
     */
}