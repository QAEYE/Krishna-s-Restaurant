package com.krishnajeena.krishnasrestaurant.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun BookTableScreen(){
  Surface(color = Color.Red,
      modifier = Modifier.fillMaxSize()
          ){
Text(text="Hello", color = Color.White,
    modifier = Modifier.wrapContentSize(
        align = Alignment.TopEnd
        ))
  }
}