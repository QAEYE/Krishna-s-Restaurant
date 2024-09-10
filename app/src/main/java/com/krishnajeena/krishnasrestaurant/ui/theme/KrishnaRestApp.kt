package com.krishnajeena.krishnasrestaurant.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun KrishnaRestApp(){

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            var selected by remember { mutableIntStateOf(1) }



            if(selected == 1) {
              HomeScreen()
            }
            else BookTableScreen()





    }
}