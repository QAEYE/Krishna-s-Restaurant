package com.krishnajeena.krishnasrestaurant.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krishnajeena.krishnasrestaurant.R

@Composable
fun ItemShowScreen(pair: Triple<String, String, Int>){

    Scaffold() { innerPadding ->
Column(modifier = Modifier.padding(innerPadding)) {
ItemShow(pair)
}
    }

}

@Composable
fun ItemShow(item: Triple<String, String, Int>){

    Card(modifier = Modifier.padding(4.dp).fillMaxWidth()
        .wrapContentHeight(),
        shape = RoundedCornerShape(4.dp),
        ){

        Column(modifier = Modifier) {
        Image(painter = painterResource(R.drawable.img1), contentDescription = null)

            Row(modifier = Modifier){
            Text(text = item.first, fontSize = 24.sp)
                Text(text = "${item.third}", fontSize = 24.sp)
            }
    }
    }


}