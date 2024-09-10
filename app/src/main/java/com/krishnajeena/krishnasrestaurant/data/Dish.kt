package com.krishnajeena.krishnasrestaurant.data

import kotlinx.serialization.Serializable

@Serializable
data class Dish(
    val image: String = "",
    val name: String = "",
    val price: String = ""
)