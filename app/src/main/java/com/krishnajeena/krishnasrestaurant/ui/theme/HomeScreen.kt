package com.krishnajeena.krishnasrestaurant.ui.theme

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.krishnajeena.krishnasrestaurant.ItemDetailsScreen
import com.krishnajeena.krishnasrestaurant.R
import com.krishnajeena.krishnasrestaurant.data.Dish

enum class KrishnasScreen(){
    HomeScreen,
    HomeScreenSeeAll
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navController1: NavHostController
){

    var isExpanded by remember { mutableStateOf(false) }


val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = KrishnasScreen.HomeScreen.toString(),
                        modifier = Modifier.fillMaxSize()
                    ){

                        composable(route = KrishnasScreen.HomeScreen.toString()){


                            var selected by remember { mutableIntStateOf(1) }

                            Scaffold(
                                bottomBar = {

                                    BottomNavigation(backgroundColor = Color.White){


                                        BottomNavigationItem(
                                            icon ={ Icon(Icons.Filled.Home, contentDescription = null) },
                                            label = null,
                                            selected = if(selected == 1) true else false,
                                            onClick = { selected = 1 }
                                        )


                                        BottomNavigationItem(
                                            icon ={ Icon(Icons.Filled.Lock, contentDescription = null) },
                                            label = null,
                                            onClick = { selected = 2 },
                                            selected = if(selected == 2) true else false
                                        )
                                    }

                                }
                            ) { innerPadding ->


                                Column(modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                                ) {

                                    if(selected == 1) {

    HomeScreenTopPart(modifier = modifier.weight(1f), navController1)
            HomeScreenBottomPart (
            modifier = Modifier.weight(1f), isExpanded,
    function = { navController.navigate(KrishnasScreen.HomeScreenSeeAll.toString())},
                itemScreenFunction = { navController1.navigate("itemDetails")}) // },


                                    }
                                    else BookTableScreen()
                                }
                            }


                        }

                        composable(route = KrishnasScreen.HomeScreenSeeAll.toString()){

                            Scaffold(
                                topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Krishna'S")
                            },
                            navigationIcon ={
                            IconButton(onClick = {navController.navigateUp()}) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                            }
                        )
                    }
                            ) { innerPadding ->
//
//                                when(krishnaUiState){
//                                    KrishnaUiState.Error -> {
//                                        TODO()
//                                    }
//                                    KrishnaUiState.Loading -> TODO()
//                                    is KrishnaUiState.Success -> {

                                        HomeScreenBottomPartFull(//animatedHeight,
                                            Modifier.padding(innerPadding),
                                            //isExpanded,
                                            onClose = {isExpanded = false},

                                            itemScreenFunction = { navController1.navigate("itemDetails")}

                                        //    data = krishnaUiState.data
                                        )
//                                    }
                             //   }




                            }
                        }

            }



        }


@Composable
fun HomeScreenTopPart(modifier: Modifier, navController1: NavHostController){

    Box(modifier = modifier.fillMaxWidth()){
        Image(painter = painterResource(R.drawable.wave), modifier = Modifier.fillMaxWidth()
            , contentScale = ContentScale.FillBounds,
            contentDescription = null,
            )

    Column(modifier= Modifier.fillMaxWidth()
    ){

        Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp,
            end = 5.dp),
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.SpaceBetween) {
       Row(modifier = Modifier
       , verticalAlignment = Alignment.CenterVertically){
        Text(text = "Hi, ", color = Color.White, fontSize = 20.sp,
            textAlign = TextAlign.Center,modifier = Modifier
            .padding(start = 10.dp, top = 7.dp, end = 0.dp, bottom = 5.dp)
        )

           Text(text = "Buddy", color = Color.White,
               textAlign = TextAlign.Center,
               fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier
               .padding(start = 10.dp, top = 8.dp, end = 0.dp, bottom = 5.dp))
       }
Row(modifier= Modifier){
           IconButton(onClick = {}, modifier = Modifier) {
               Icon(imageVector = Icons.Filled.ShoppingCart, tint = Color.White, contentDescription = null)

       }
            IconButton(onClick = {navController1.navigate("myprofile")}, modifier = Modifier) {
                Icon(imageVector = Icons.Filled.Person, tint = Color.White, contentDescription = null)
            }
}
       }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            , verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "Book your",
                    textAlign = TextAlign.Center,
                    lineHeight = 5.sp,
                    fontFamily = FontFamily(
                        Font(R.font.antic_slab, FontWeight.Normal)
                    ),
                    fontSize = 45.sp,
                    color = Color.White,
                    modifier = Modifier

                        .padding(start = 12.dp, top = 3.dp, bottom = 0.dp, end = 0.dp)

                )

                Text(text = "table and order",
                    textAlign = TextAlign.Center,
                    lineHeight = 5.sp,
                    fontFamily = FontFamily(
                        Font(R.font.antic_slab, FontWeight.Normal)
                    ),
                    fontSize = 35.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp, bottom = 0.dp, end = 0.dp))

                Text(text = "with ease",
                    textAlign = TextAlign.Center,
                    lineHeight = 5.sp,
                    fontFamily = FontFamily(
                        Font(R.font.antic_slab, FontWeight.Normal)
                    ),
                    fontSize = 45.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp, bottom = 0.dp, end = 0.dp))
                Text(text = "at home",
                    textAlign = TextAlign.Center,
                    lineHeight = 5.sp,
                    fontFamily = FontFamily(
                        Font(R.font.antic_slab, FontWeight.Normal)
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp, bottom = 0.dp, end = 0.dp))


            }

            Image(painter = painterResource(R.drawable.restwithout), contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .scale(1.3f))
        }

    }
    }
}

@Composable
fun HomeScreenBottomPart(
    modifier: Modifier, isExpanded: Boolean, function: () -> Unit, itemScreenFunction: () -> Unit,
//                         data : List<Dish>
){

    val scroll = rememberScrollState()

    val context = LocalContext.current

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(scroll)){

        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Category", fontSize = 30.sp,
                maxLines = 1, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = 10.dp))
           Spacer(modifier = Modifier.weight(2f))
            Text(text = "See all", modifier = Modifier
                .weight(1f)
                .clickable {
                    function.invoke()
                }
                ,
                textAlign = TextAlign.Center)
        }

        var selectedIndex by rememberSaveable { mutableStateOf(1) }

        val homeViewModel: HomeViewModel = //key(selectedIndex) {
            viewModel(factory = HomeViewModel.Factory)
        //}
        val krishnaUiState = homeViewModel.krishnaUiState

        var data by remember{mutableStateOf(listOf(Dish()))}

        when(krishnaUiState) {
            is KrishnaUiState.Success -> {
                data = krishnaUiState.data

            }

            KrishnaUiState.Error -> EmptyScreen()
            KrishnaUiState.Loading -> EmptyScreen()
            else -> Unit
        }

//        var list by rememberSaveable{ mutableStateOf(viewModel.getDishStateList())
//        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)) {
            Text("Breakfast", modifier = Modifier
                .weight(1f)
                .clickable {
                    //homeViewModel.dataT = 1
Log.i("AAAF!!!!!!!!", "First 1")
                    selectedIndex = 1
                    homeViewModel.getDishStateList(1)
                },
                fontWeight = if(selectedIndex == 1) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center)
            Text("Lunch", modifier = Modifier
                .weight(1f)
                .clickable {
                    //     list = list2
//                    viewModel.setDishtList(2)
//                    list = viewModel.getDishStateList()
                    //homeViewModel.dataT = 2
                    Log.i("AAAF!!!!!!!!", "Second 2")
                    selectedIndex = 2
                    homeViewModel.getDishStateList(2)
                },
                fontWeight = if(selectedIndex == 2) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center)
            Text("Dinner", modifier = Modifier
                .weight(1f)
                .clickable {
                    //     list = list3
//                    viewModel.setDishtList(3)
//                    list = viewModel.getDishStateList()
                   // homeViewModel.dataT = 3
                    Log.i("AAAF!!!!!!!!", "Third 3")
                    selectedIndex = 3
                    homeViewModel.getDishStateList(3)
                },
                fontWeight = if(selectedIndex == 3) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center)
        }


        LazyRow(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = data){
                item ->
                    ItemsScreen(item, context, itemScreenFunction)

            }
        }

    }

    }

@Composable
fun EmptyScreen(){

}

@Composable
fun HomeScreenBottomPartFull(
   // animatedHeight: Dp,
    modifier: Modifier,
   // isExpanded: Boolean,
    onClose: () -> Unit,
    itemScreenFunction: () -> Unit,
   // data : List<Dish>
){

   // if (isExpanded) {
        Box(modifier = modifier
            .fillMaxSize()
            .padding(top = 5.dp)
            ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top part with categories

                var selectedIndex by rememberSaveable { mutableStateOf(1) }

                val homeViewModel: HomeViewModel = //key(selectedIndex) {
                    viewModel(factory = HomeViewModel.Factory)
                //}
                val krishnaUiState = homeViewModel.krishnaUiState

                var data by remember{mutableStateOf(listOf(Dish()))}

                when(krishnaUiState) {
                    is KrishnaUiState.Success -> {
                        data = krishnaUiState.data

                    }

                    KrishnaUiState.Error -> EmptyScreen()
                    KrishnaUiState.Loading -> EmptyScreen()
                    else -> Unit
                }

                val context = LocalContext.current

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Breakfast", modifier = Modifier
                        .padding(
                            top = 0.dp,
                            start = 8.dp
                        )
                        .clickable {
                            selectedIndex = 1
                            homeViewModel.getDishStateList(selectedIndex)
                        },
                        fontWeight = if(selectedIndex == 1) FontWeight.Bold else FontWeight.Normal,)

                    Text(text = "Lunch", modifier = Modifier
                        .padding(top = 0.dp)
                        .clickable {
                            selectedIndex = 2
                            homeViewModel.getDishStateList(selectedIndex)
                        },
                        fontWeight = if(selectedIndex == 2) FontWeight.Bold else FontWeight.Normal,)

                    Text(text = "Dinner", modifier = Modifier
                        .padding(
                            top = 0.dp,
                            end = 8.dp
                        )
                        .clickable {
                            selectedIndex = 3
                            homeViewModel.getDishStateList(selectedIndex)
                        },
                        fontWeight = if(selectedIndex == 3) FontWeight.Bold else FontWeight.Normal,)
                }




                // Selected category dishes
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(4.dp),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    items(data) { dish ->
                        // Display each dish in a vertical list
                        ItemsScreen(dish, context, itemScreenFunction)
                    }
                }

                // Close button
                Button(onClick = onClose, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Close")
                }
            }
        }
    //}

}


@Composable
fun ItemsScreen(pair: Dish, context: Context, itemScreenFunction: () -> Unit){

 //   val controller = rememberNavController()

    var currentDish : Dish by remember { mutableStateOf(pair) }
//    NavHost(navController = controller, startDestination = "itemDish"){
//
//
//        composable("itemDish"){

            Card(modifier = Modifier
                .padding(16.dp)
                .width(180.dp)
                .wrapContentHeight()
                .clickable {


                    Toast
                        .makeText(context, "Baklol karte rho!", Toast.LENGTH_LONG)
                        .show()

                    itemScreenFunction.invoke()


                },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp),

                ) {

                Column(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                        .data(pair.image)
                        .build(),
                        placeholder = painterResource(R.drawable.vecteezy_vector_loading_icon_template_black_color_editable_vector_6692205),
                        error = painterResource(R.drawable.icons8_broken_image_48),

                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(8.dp)))

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center){

                        Text(text = pair.name,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            softWrap = true,
                            fontWeight = FontWeight.Bold
                            ,modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = pair.price,
                            fontSize = 18.sp,
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Bold
                            ,modifier = Modifier.padding(start = 8.dp,
                                end = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }


//        }
//
//        composable("detailsScreen"){
//
//            ItemDetailsScreen(modifier = Modifier.fillMaxSize(), currentDish)
//
//        }

    }



