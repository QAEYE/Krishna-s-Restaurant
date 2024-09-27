package com.krishnajeena.krishnasrestaurant.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthGuestSignInViewModel: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
 private val _authState = MutableLiveData<GuestSignInState>()
    val authState : LiveData<GuestSignInState> = _authState

    init {
        checkAuthState()
    }

    fun checkAuthState(){
        if(auth.currentUser?.isAnonymous == true){
            _authState.value = GuestSignInState.Success
        }
        else {
            _authState.value = GuestSignInState.Failure
        }
    }

    fun guestSuccess(){
        _authState.value = GuestSignInState.Success
    }


    fun guestFailure(){
        _authState.value = GuestSignInState.Failure
    }
}


sealed class GuestSignInState{
    object Success : GuestSignInState()
    object Failure : GuestSignInState()
}