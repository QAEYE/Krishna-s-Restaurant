package com.krishnajeena.krishnasrestaurant

import android.app.Application
import com.google.firebase.FirebaseApp
import com.krishnajeena.krishnasrestaurant.data.AppContainer
import com.krishnajeena.krishnasrestaurant.data.DefaultAppContainer

class KrishnaRestApplicationContainer : Application() {

    lateinit var container: AppContainer

            override fun onCreate(){
                super.onCreate()
                FirebaseApp.initializeApp(this)
                container = DefaultAppContainer()
            }

}