package com.example.spliteasy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SplitViewModelFactory(val context: Context) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplitViewModel::class.java)){
            return SplitViewModel(context = context ) as T
        }
        throw IllegalArgumentException("Type anything useful here as exception")
    }
}