package com.krishnajeena.krishnasrestaurant

import android.content.Context
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.krishnajeena.krishnasrestaurant.data.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID

class GoogleSignInViewModel: ViewModel() {

    val user = MutableLiveData<User>(null)

    fun handleGoogleSignIn(context: Context, navController: NavController){
        viewModelScope.launch{

            googleSignIn(context).collect{
                result ->
                result.fold(
                    onSuccess = {authResult ->
                        val currentUser = authResult.user
                        if(currentUser != null){
                            user.value = User(currentUser.uid, currentUser.displayName!!, currentUser.email!!)

                            Toast.makeText(context,
                                "Account created successfully",
                            Toast.LENGTH_LONG).show()

                            navController.popBackStack()
                            navController.navigate("main")

                        }
                    },
                    onFailure = {
                        e->
                        Toast.makeText(context,
                            "$e Something went wrong",
                            Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }

    private suspend fun googleSignIn(context: Context): Flow<Result<AuthResult>>{

        val firebaseAuth = FirebaseAuth.getInstance()

        return callbackFlow {
            try {
                val credentialManager: CredentialManager = CredentialManager.create(context)

                val ranNonce: String = UUID.randomUUID().toString()
                val bytes: ByteArray = ranNonce.toByteArray()
                val md: MessageDigest = MessageDigest.getInstance("SHA-256")
                val digest: ByteArray = md.digest(bytes)
                val hashedNonce: String = digest.fold(""){ str, it -> str+"%02x".format(it)}

            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.web_id))
                .setNonce(hashedNonce)
                .setAutoSelectEnabled(true)
                .build()

                val request: GetCredentialRequest = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val result = credentialManager.getCredential(context, request)
                val credential = result.credential

                if(credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    val authResult = firebaseAuth.signInWithCredential(authCredential).await()

                    trySend(Result.success(authResult))
                }
                else {
                    throw RuntimeException("Received an invalid")
                }

            } catch(e: GetCredentialCancellationException){
                trySend(Result.failure(Exception("Sign in was cancelled")))
            } catch(e: Exception){
                trySend(Result.failure(Exception("This is What $e")))
            }

awaitClose{}


        }
    }

}