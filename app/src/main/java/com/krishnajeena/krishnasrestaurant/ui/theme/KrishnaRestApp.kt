package com.krishnajeena.krishnasrestaurant.ui.theme

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

import com.krishnajeena.krishnasrestaurant.AuthenticationScreen
import com.krishnajeena.krishnasrestaurant.ItemDetailsScreen
import com.krishnajeena.krishnasrestaurant.data.Dish

@Composable
fun KrishnaRestApp(context: Context){

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            val navController = rememberNavController()


            val auth = FirebaseAuth.getInstance()
            val isUserSignedIn = remember { mutableStateOf(auth.currentUser != null) }
            FirebaseAuth.AuthStateListener { auth ->
                isUserSignedIn.value = auth.currentUser != null
            }

            val start = if(isUserSignedIn.value) "main" else "authentication"
            NavHost(navController, start){
                composable("authentication"){

               AuthenticationScreen(navController, context, rememberCoroutineScope())

                }

                composable("main"){

                    var selected by remember { mutableIntStateOf(1) }

                    if(selected == 1) {
                        HomeScreen(navController = navController)
                    }
                    else BookTableScreen()

                }

                composable("itemDetails"){

                    ItemDetailsScreen(modifier = Modifier, Dish("", "Khawarizmiya", "$4.32"))

                }

            }

    }
}