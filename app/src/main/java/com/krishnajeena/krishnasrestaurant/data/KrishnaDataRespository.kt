package com.krishnajeena.krishnasrestaurant.data

import com.krishnajeena.krishnasrestaurant.network.DishesApiService

interface KrishnaDataRespository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getKrishnaData(dataT : Int): List<Dish>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkKrishnaDataRepository(
    private val dishesApiService: DishesApiService
) :  KrishnaDataRespository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getKrishnaData(dataT: Int): List<Dish> =
        when (dataT) {
            1 -> dishesApiService.getDishes()
            2 -> dishesApiService.getDishesLunch()
            else -> dishesApiService.getDishesDinner()
        }
}