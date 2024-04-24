package com.example.spliteasy.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.spliteasy.db.AppDatabase
import com.example.spliteasy.db.model.ExpenseData
import com.example.spliteasy.db.model.ExpenseWithUser
import com.example.spliteasy.db.model.TransactionData
import com.example.spliteasy.db.model.Trip
import com.example.spliteasy.db.model.User
import com.example.spliteasy.repository.Repo
import com.example.spliteasy.ui.model.ExpenseModel
import com.example.spliteasy.ui.model.ExpenseUserTransactionModel
import com.example.spliteasy.ui.model.SettlementModel
import com.example.spliteasy.ui.model.SplitUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class SplitViewModel(context: Context) : ViewModel() {
    var db = AppDatabase.initDB(context)
    var repo = Repo(db)

    var selectedTrip: Trip? = null
    var selectedExpense: ExpenseData? = null
    var selectedPaidBy: User? = null

    //MutableList
    private var _usersList = MutableLiveData<List<User>>()
    private var _tripUser = MutableLiveData<List<User>>()
    private var _splitUser = MutableLiveData<List<User>?>()
    private var _payByUserList = MutableLiveData<List<User>?>()
    private var _tripList = MutableLiveData<List<Trip>>()
    private var _expenseList = MutableLiveData<List<ExpenseData>>()
    private var _transactionList = MutableLiveData<List<TransactionData>>()
    private var _tripTransactionList = MutableLiveData<List<TransactionData>>()
    var list = arrayListOf<ExpenseUserTransactionModel>()
    val settelment = MutableLiveData<List<ExpenseUserTransactionModel>>()
    val _finalSettlementList = MutableLiveData<List<SettlementModel>>()


    //LiveData List
    val users: LiveData<List<User>> = _usersList
    val tripUser: LiveData<List<User>> = _tripUser
    val payByUserList: LiveData<List<User>?> = _payByUserList
    val splitUserList: LiveData<List<User>?> = _splitUser
    val trip: LiveData<List<Trip>> = _tripList
    val expenses: LiveData<List<ExpenseData>> = _expenseList
    val transactionList: LiveData<List<TransactionData>> = _transactionList
    val tripTransactionList: LiveData<List<TransactionData>> = _tripTransactionList
    val settlementMap: LiveData<List<ExpenseUserTransactionModel>> = settelment
    val finalSettlementList: LiveData<List<SettlementModel>> = _finalSettlementList


    init {
        viewModelScope.launch(Dispatchers.Main)
        {
            getTrip()
            getUsers()
        }
    }

    suspend fun getUsers() {
        _usersList.value = repo.getUser()
    }

    suspend fun getTrip() {
        _tripList.value = repo.getTrip()
    }

    suspend fun getTripWithUsers() {
        _tripUser.value = repo.getTripWithUser(selectedTrip!!.trip_id.toLong())
    }

    suspend fun getExpense() {
        list.clear()

        _expenseList.value = repo.getExpense(selectedTrip!!.trip_id.toLong())

        //GetTrip wise Expense
        _payByUserList.value = _expenseList.value!!.map {
            repo.getUserWithId(it.paidByUserId)
        }

        getTransactionAsTripId()

        //GetExpense Wise Split User
        _expenseList.value!!.forEachIndexed { index, x ->

            var splitMember = arrayListOf<SplitUser>()

            var transactionAll = repo.getTransaction(x.expense_id.toLong())
            transactionAll.map {
                splitMember.add(
                    SplitUser(
                        user = repo.getUserWithId(it.user_id),
                        splitAmount = it.amount
                    )
                )
            }
            list.add(
                ExpenseUserTransactionModel(
                    paidBy = _payByUserList.value!![index],
                    amount = x.totalAmount,
                    splitUserList = splitMember
                )
            )
        }


//        countSettlement()
        newCountSettlement()

    }

    suspend fun getExpenseTransaction() {
        _transactionList.value = repo.getTransaction(selectedExpense!!.expense_id.toLong())
        _splitUser.value = _transactionList.value!!.map {
            repo.getUserWithId(it.user_id)
        }
    }

    suspend fun getTransactionAsTripId() {
        _tripTransactionList.value = repo.getTransactionAsTripID(selectedTrip!!.trip_id.toLong())
    }


    //Equal amount split
    fun splitCalculator(expenseList: List<ExpenseModel>, amount: Double): List<ExpenseModel> {
        val selectedUsers = expenseList.filter { it.selected }
        if (selectedUsers.isEmpty()) {
            return expenseList
        }

        val amountPerUser: Double = (amount / selectedUsers.size).toDouble()
        val updateUser = expenseList.map {
            if (it.selected) {
                ExpenseModel(user = it.user, selected = it.selected, amount = amountPerUser)
            } else {
                ExpenseModel(user = it.user, selected = it.selected, amount = 0.0)
            }
        }
        return updateUser
    }

    fun newCountSettlement(){
        var creditUserList = List(tripUser.value!!.size){
                it -> ExpenseUserTransactionModel(paidBy = tripUser.value!![it], amount = 0);
        }.toMutableList()
        var debitUserList = List(tripUser.value!!.size){ it -> ExpenseUserTransactionModel(paidBy = tripUser.value!![it], amount = 0);
        }.toMutableList()
        for( x in tripUser.value!!)
        {
            var sameUserList = list.filter {
                it.paidBy.user_id == x.user_id
            }
            var sameUserExpenseList = _tripTransactionList.value!!.filter {
                x.user_id == it.user_id
            }

            if (sameUserExpenseList.isNotEmpty()) {
                var sumOfSingleUserExpense = sameUserExpenseList.sumOf {
                    it.amount
                }

                debitUserList.mapIndexed(){index, it ->
                    if(debitUserList[index].paidBy.user_id ==x.user_id) {
                        debitUserList[index]= ExpenseUserTransactionModel(
                            paidBy = x,
                            amount = sumOfSingleUserExpense.toInt()
                        )
                    }
                }
            }

            if (sameUserList.isNotEmpty()) {
                var sumPaidAmount = sameUserList.sumOf {
                    it.amount
                }
//                Log.e("TAG", "Paid UserList User list: ${x.name} ${sumPaidAmount}")

//                paidUserList.add(ExpenseUserTransactionModel(paidBy = x, amount = sumPaidAmount))

                creditUserList.mapIndexed() {index,it->
                    if(it.paidBy.user_id == x.user_id) {
                        creditUserList[index] = ExpenseUserTransactionModel(paidBy = x, amount = sumPaidAmount)
                    }
                }

            }
        }

        creditUserList.map {
            Log.e("TAG", "Credit: ${it.paidBy} = ${it.amount}" )
        }

        Log.i("TAG", "newCountSettlement: ================================")

        debitUserList.map {
            Log.e("TAG", "Debit: ${it.paidBy} = ${it.amount}" )
        }

        var settelmentList = arrayListOf<ExpenseUserTransactionModel>()

        for (i in 0..<debitUserList.size) {
            //Amount = totalExpense - UserExpense

            settelmentList.add(
                ExpenseUserTransactionModel(
                    paidBy = creditUserList[i].paidBy,
                    amount = creditUserList.get(i).amount - debitUserList.get(i).amount
                )
            )
        }

        Log.i("TAG", "newCountSettlement: ================================")

        settelmentList.map {
            Log.e("TAG", "newCountSettlement: ${it.paidBy.name} ${it.amount}" )
        }

        var fromList = settelmentList.filter {
            it.amount < 0
        }

        var toList = settelmentList.filter {
            it.amount > 0
        }


        Log.d("TAG", "From list ====== ")

        fromList.map {
            Log.e("TAG", "countSettlement1: ${it.paidBy.name} ${it.amount}")
        }

        Log.d("TAG", "To list ====== ")

        toList.map {
            Log.e("TAG", "countSettlement2: ${it.paidBy.name} ${it.amount}")
        }

        var finalList = arrayListOf<SettlementModel>()

        for (i in fromList.indices) {
            fromList[i].amount = Math.abs(fromList[i].amount)

            for (j in toList.indices) {
                if (fromList[i].amount > toList[j].amount) {
                    fromList[i].amount = fromList[i].amount - toList[j].amount
                    finalList.add(
                        SettlementModel(
                            from = fromList[i].paidBy.name,
                            to = toList[j].paidBy.name,
                            amount = toList[j].amount.toDouble()
                        )
                    )
                    toList[j].amount=0
                }
                else if (fromList[i].amount <= toList[j].amount) {
                    toList[j].amount = toList[j].amount - fromList[i].amount
                    finalList.add(
                        SettlementModel(
                            from = fromList[i].paidBy.name,
                            to = toList[j].paidBy.name,
                            amount = fromList[i].amount.toDouble()
                        )
                    )
                    fromList[i].amount = 0
                    break
                }
            }
        }
        Log.i("TAG", "newCountSettlement: ================================")

        _finalSettlementList.value = finalList
        finalList.map { Log.e("TAG", "countSettlement3: ${it.from} > ${it.to} = ${it.amount}") }
    }

}