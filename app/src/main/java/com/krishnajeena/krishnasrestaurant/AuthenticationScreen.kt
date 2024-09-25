package com.krishnajeena.krishnasrestaurant

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.krishnajeena.krishnasrestaurant.model.AuthEmailViewModel
import com.krishnajeena.krishnasrestaurant.model.AuthState
import kotlinx.coroutines.CoroutineScope


@Composable
fun AuthenticationScreen(navController: NavHostController, context: Context, coroutineScope: CoroutineScope) {

    val navCon = rememberNavController()

    NavHost(navCon,"SignUp"){

        composable("SignUp"){

            SignUpScreen(navCon, navController , context, coroutineScope)

        }

        composable("NewAccount"){

            CreateNewAccount(navCon, navController, authEmailViewModel = AuthEmailViewModel())

        }

        composable("SignIn"){

            SignInScreen(navCon)

        }


    }


}



@Composable
fun SignUpScreen(navController: NavHostController, navCon: NavHostController,
                 context: Context, coroutineScope: CoroutineScope) {

    Column(modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

Column( horizontalAlignment = Alignment.CenterHorizontally,){
        Text("You will get personalized experience",
            fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp),
            style = androidx.compose.ui.text.TextStyle(color = Color(0xFF8A8A8A))
        )

        Text("Sign up and order\nyour food", minLines = 2,
            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
            fontSize = 34.sp)
}
        Image(painter = painterResource(R.drawable.restwithout),
            contentDescription = null, modifier = Modifier.padding(20.dp))

        Column(modifier = Modifier.align(Alignment.CenterHorizontally)){

        Column(modifier = Modifier.padding(bottom = 40.dp)
            .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(onClick = {}, modifier = Modifier.width(260.dp)) {

            Icon(painter = painterResource(R.drawable.icons8_facebook_48),
                tint = Color.Unspecified,
                contentDescription = null, modifier = Modifier.padding(end = 3.dp)
                    .size(40.dp))
            Text("Sign Up with Facebook")

        }



        OutlinedButton(onClick ={

            val googleSignInViewModel = GoogleSignInViewModel()
            googleSignInViewModel.handleGoogleSignIn(context, navCon)

        }, modifier = Modifier.width(260.dp)) {

            Icon(painter = painterResource(R.drawable.icons8_google_50),
                 modifier = Modifier.padding(end = 3.dp).size(40.dp),
                tint = Color.Unspecified,
                contentDescription = null)
            Text("Sign Up with Google")

        }


        OutlinedButton(onClick = {}, modifier = Modifier.width(260.dp)
            .size(55.dp)) {

            Icon(painter = painterResource(R.drawable.icons8_x_50),
                tint = Color.Unspecified,
                contentDescription = null)
            Text("Sign Up with X")



        }
        }

        Row(modifier = Modifier.padding(bottom = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
Divider(modifier = Modifier.width(140.dp)
    .padding(end = 14.dp), thickness = 2.dp)
            Text("Or", fontSize = 14.sp, fontWeight = FontWeight.Bold)

            Divider(modifier = Modifier.width(140.dp).padding(start = 14.dp), thickness = 2.dp)
        }

            FilledTonalButton(onClick = {
                navController.navigate("NewAccount")
            }, modifier = Modifier
                .padding(bottom = 40.dp).align(Alignment.CenterHorizontally)
                .width(280.dp).height(60.dp)) {
                Text("Create new account")
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)
                ){

                Text("Already have an account?",
                    modifier = Modifier.padding(end = 4.dp))
                Text(text = "Sign in", modifier = Modifier.clickable {
                    navController.navigate("SignIn")
                }, color = Color(0xFFFF5621), fontWeight = FontWeight.Bold)
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text("Enter as guest!",
                    fontSize = 14.sp, modifier = Modifier
                        .clickable {
                            Firebase.auth.signInAnonymously()
                                .addOnCompleteListener(){
                                    task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInAnonymously:success")
                                        //val user = auth.currentUser
                                        navCon.navigate("main")
                                      //  updateUI(user)
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                                        Toast.makeText(
                                            context,
                                            "Authentication failed.",
                                            Toast.LENGTH_SHORT,
                                        ).show()
                                       // updateUI(null)
                                    }
                                }
                        })
            }

        }
    }

}

fun handleSignIn(result: GetCredentialResponse) {
    // Handle the successfully returned credential.
    val credential = result.credential

    when (credential) {

        // Passkey credential
        is PublicKeyCredential -> {
            // Share responseJson such as a GetCredentialResponse on your server to
            // validate and authenticate
           // responseJson = credential.authenticationResponseJson
        }

        // Password credential
        is PasswordCredential -> {
            // Send ID and password to your server to validate and authenticate.
            val username = credential.id
            val password = credential.password
        }

        // GoogleIdToken credential
        is CustomCredential -> {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    // Use googleIdTokenCredential and extract the ID to validate and
                    // authenticate on your server.
                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(credential.data)
                    // You can use the members of googleIdTokenCredential directly for UX
                    // purposes, but don't use them to store or control access to user
                    // data. For that you first need to validate the token:
                    // pass googleIdTokenCredential.getIdToken() to the backend server.
                //    GoogleIdTokenVerifier verifier = ... // see validation instructions
                  //  GoogleIdToken idToken = verifier.verify(idTokenString);
                    // To get a stable account identifier (e.g. for storing user data),
                    // use the subject ID:
                    //idToken.getPayload().getSubject()
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e(TAG, "Received an invalid google id token response", e)
                }
            } else {
                // Catch any unrecognized custom credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }

        else -> {
            // Catch any unrecognized credential type here.
            Log.e(TAG, "Unexpected type of credential")
        }
    }
}

@Composable
fun CreateNewAccount(
    navController: NavHostController,
    navController1: NavHostController,
    authEmailViewModel: AuthEmailViewModel,

){
Scaffold(modifier = Modifier) {
    innerPadding->

    val context = LocalContext.current
    var email by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}

    val authState = authEmailViewModel.authState.observeAsState()
    LaunchedEffect(authState) {
        when(authState.value)
        {
            is AuthState.Authenticated -> navController1.navigate("main")
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Create new account", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = email,
            onValueChange = {email=it},
            label = {Text("Enter your email address")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password,
            onValueChange = {password=it},
            label = {Text("Enter a password")}
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

@Composable
fun SignInScreen(navController: NavHostController){
    Scaffold(modifier = Modifier) {
            innerPadding->

        var email by remember{mutableStateOf("")}
        var password by remember{mutableStateOf("")}

        Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text("Sign in to your account", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = email,
                onValueChange = {email=it},
                label = {Text("Enter your email address")}
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password,
                onValueChange = {password=it},
                label = {Text("Enter your password")}
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = {}) {
                Text("Sign In")
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {navController.navigate("SignUp")}) {
                Text("Go back!")
            }

        }
    }



}
