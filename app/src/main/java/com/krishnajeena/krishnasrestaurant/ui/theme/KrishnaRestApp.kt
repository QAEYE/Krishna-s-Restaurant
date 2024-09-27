package com.krishnajeena.krishnasrestaurant.ui.theme

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth

import com.krishnajeena.krishnasrestaurant.ItemDetailsScreen
import com.krishnajeena.krishnasrestaurant.SignInScreen
import com.krishnajeena.krishnasrestaurant.SignUpScreen
import com.krishnajeena.krishnasrestaurant.data.Dish
import com.krishnajeena.krishnasrestaurant.model.AuthEmailViewModel
import com.krishnajeena.krishnasrestaurant.model.AuthGuestSignInViewModel
import com.krishnajeena.krishnasrestaurant.model.AuthState

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

            val start = if(isUserSignedIn.value) "main" else "SignUp"

                NavHost(navController, start){

                composable("main"){

                    var selected by remember { mutableIntStateOf(1) }

                    if(selected == 1) {
                        HomeScreen(navController1 = navController)
                    }
                    else BookTableScreen()

                }

                composable("itemDetails"){

                    ItemDetailsScreen(modifier = Modifier, Dish("", "Khawarizmiya", "$4.32"))

                }


                    composable("SignUp"){

                        SignUpScreen(navController , context,

                        )

                    }

                    composable("NewAccount"){

                        CreateNewAccount(navController, authEmailViewModel = AuthEmailViewModel(),
                            context)

                    }

                    composable("SignIn"){

                        SignInScreen(navController, authEmailViewModel = AuthEmailViewModel())

                    }

                    composable("myprofile"){
                        MyProfile(navController)
                    }

            }

    }
}

@Composable
fun MyProfile(navController: NavHostController) {

    Scaffold(modifier = Modifier){
        innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)
            , contentAlignment = Alignment.Center){
        Column(modifier = Modifier, verticalArrangement = Arrangement.Center){

            val authEmailViewModel = AuthEmailViewModel()
            val state = authEmailViewModel.authState.observeAsState()

            LaunchedEffect(state.value) {
                when(state.value){
                    is AuthState.Unauthenticated  -> {
                        navController.navigate("SignUp")
                    }
                    else -> Unit

                }
            }

            Button(onClick = {

                authEmailViewModel.signout()

            }) {
                Text("Sign Out", fontSize = 20.sp)
            }

        }
        }

    }

}

@Composable
fun CreateNewAccount(
    navController: NavHostController,
    //  navController1: NavHostController,
    authEmailViewModel: AuthEmailViewModel,
    context: Context

){
    Scaffold(modifier = Modifier) {
            innerPadding->

        var email by remember{mutableStateOf("")}
        var password by remember{mutableStateOf("")}

        val authState = authEmailViewModel.authState.observeAsState()
        LaunchedEffect(authState.value) {
            when(authState.value)
            {
                is AuthState.Authenticated ->{
                    Log.i("TAG:::", "navigating")
                    //  navController.navigate("SignUp")
                    navController.navigate("main"){
                        popUpTo("SignUp")
                    }
                }
                is AuthState.Error -> Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text("Create new account", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = email,
                onValueChange = {email=it},
                label = { Text("Enter your email address") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password,
                onValueChange = {password=it},
                label = { Text("Enter a password") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = {
                authEmailViewModel.signup(email, password)
            }) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {navController.navigate("SignIn")}) {
                Text("Already have an account?")
            }

        }
    }
}