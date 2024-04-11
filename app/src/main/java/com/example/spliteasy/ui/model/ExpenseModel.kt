package com.example.spliteasy.ui.model

import com.example.spliteasy.db.model.User

data class ExpenseModel(val user:User, val selected:Boolean,var amount:Double)