package com.krishnajeena.krishnasrestaurant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.krishnajeena.krishnasrestaurant.data.Dish
import org.jetbrains.annotations.Async

@Composable
fun ItemDetailsScreen(modifier: Modifier = Modifier, dish: Dish) {

      DetailsTopPart(modifier, dish.image)
    DetailsBottomPart(dish = dish)

}

@Composable
fun DetailsTopPart(modifier: Modifier, dishPhoto: String){

    Box(modifier = modifier){

        AsyncImage(model = ImageRequest.Builder(
            context = LocalContext.current
        ).data(dishPhoto).build(),
            placeholder = painterResource(R.drawable.vecteezy_vector_loading_icon_template_black_color_editable_vector_6692205),
            error = painterResource(R.drawable.icons8_broken_image_48),

            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(8.dp)))

        Row(modifier = Modifier){
         IconButton(onClick = {}, modifier = Modifier) {
             Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                 contentDescription = null)
         }

            Spacer(modifier = Modifier.width(120.dp))

            IconButton(onClick = {}, modifier = Modifier) {
                Icon(imageVector = Icons.Filled.AddCircle,
                    contentDescription = null)
            }
            
        }

    }

}

@Composable
fun DetailsBottomPart(modifier: Modifier = Modifier, dish: Dish) {

    var quantity by remember{mutableStateOf(1)}

    Column(modifier = Modifier){

    Row {
        Text(text = dish.name)

        Spacer(modifier = Modifier.width(100.dp))

        Row {
            IconButton(onClick = {if(quantity!=1)quantity--}) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)}
            Text(text = "$quantity", fontSize = 20.sp)
            IconButton(onClick = {if(quantity<6)quantity++}){
            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)}

        }


    }
        Text("lajldjf sdlfjds sdlfkjsd sdlkjdfj sdlkjf  ldfklsjdf kalsdfj lkajsdf lsdlfk")

    }

    
}