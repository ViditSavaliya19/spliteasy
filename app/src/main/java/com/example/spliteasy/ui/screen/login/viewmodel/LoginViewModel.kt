//package com.example.spliteasy.ui.screen.login.viewmodel
//
//import androidx.lifecycle.ViewModel
//import com.example.spliteasy.domain.FireAuthHelper
//
//class LoginViewModel :ViewModel() {
//    var isLogin = false
//    init {
//        checkUser()
//    }
//    suspend fun signUp(email:String,password:String): Boolean {
//        return FireAuthHelper.helper.signUp(email,password)
//    }
//
//     suspend  fun signIn(email:String,password:String): Boolean {
//        return FireAuthHelper.helper.signIn(email,password)
//    }
//
//    fun checkUser()
//    {
//        isLogin = FireAuthHelper.helper.currentUser()
//    }
//    fun singOut()
//    {
//         FireAuthHelper.helper.signOut()
//        checkUser()
//    }
//
//
//
//}