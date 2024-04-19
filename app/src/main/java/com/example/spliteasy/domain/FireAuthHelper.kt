package com.example.spliteasy.domain

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.math.log

class FireAuthHelper {

    companion object {
        val helper: FireAuthHelper = FireAuthHelper()
    }

    private val auth: FirebaseAuth = Firebase.auth

    fun signUp(email: String, password: String): Boolean {
        var isResult = auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.e("Auth", "signUp: Success")
        }.addOnFailureListener {
            Log.e("Auth", " Error signUp: ${it.localizedMessage}")
        }
        return isResult.isSuccessful
    }

  suspend  fun signIn(email: String, password: String): Boolean {
        var isResult = auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.e("Auth", "signIn: Success")
        }.addOnFailureListener {
            Log.e("Auth", " Error signIn: ${it.localizedMessage}")
        }
        return isResult.isSuccessful
    }

    fun currentUser(): Boolean {
        val currentUser = auth.currentUser
        if (currentUser != null) {
           return true
        }
        return false
    }

    fun signOut()
    {
        auth.signOut()
    }




}