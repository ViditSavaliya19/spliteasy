package com.example.spliteasy.ui.model

import com.example.spliteasy.db.model.User

class ExpenseUserTransactionModel(
    var paidBy:User, var amount: Int, var splitUserList:List<SplitUser> = arrayListOf()
)

class SplitUser(val user: User,val splitAmount:Double)