package com.example.spliteasy.repository

import com.example.spliteasy.db.AppDatabase
import com.example.spliteasy.db.model.ExpenseData
import com.example.spliteasy.db.model.ExpenseWithUser
import com.example.spliteasy.db.model.TransactionData
import com.example.spliteasy.db.model.Trip
import com.example.spliteasy.db.model.TripUser
import com.example.spliteasy.db.model.User

class Repo(val db: AppDatabase) {
    //User
    fun addUser(user: User) = db.dao().addUser(user)
    fun updateUser(user: User) = db.dao().updateUser(user)
    fun deleteUser(user: User) = db.dao().deleteUser(user)
    suspend fun getUser(): List<User> = db.dao().getUsers()
    suspend fun getUserWithId(id : Int): User = db.dao().getMultipleUsers(id)

    //Trip
    suspend fun addTrip(trip: Trip): Long = db.dao().addTrip(trip)
    fun updateTrip(trip: Trip) = db.dao().updateTrip(trip)
    fun deleteTrip(trip: Trip) = db.dao().deleteTrip(trip)
    suspend  fun getTrip(): List<Trip> = db.dao().getTrips()

    //TripUsers
    suspend fun addTripUsers(tripUser: List<TripUser>) = db.dao().addTripUsers(tripUser)
    fun updateTripUser(tripUser: TripUser) = db.dao().updateTripUsers(tripUser)
    fun deleteTripUser(tripUser: TripUser) = db.dao().deleteTripUsers(tripUser)

   suspend fun getTripUsers(): List<TripUser> = db.dao().getTripsUser()
   suspend fun getTripWithUser(id:Long): List<User> = db.dao().getTripWithUsers(id)


    //Expense
    suspend fun addExpense(expense: ExpenseData):Long = db.dao().addExpense(expense)
    fun updateExpense(expense: ExpenseData) = db.dao().updateExpense(expense)
    fun deleteExpense(expense: ExpenseData) = db.dao().deleteExpense(expense)

    suspend fun getExpense(tripId: Long): List<ExpenseData> = db.dao().getExpense(tripId)


    //Transaction
    suspend fun addTransaction(transaction: List<TransactionData>) = db.dao().addTransaction(transaction)
    fun updateTransaction(transactionData: List<TransactionData>) = db.dao().updateTransaction(transactionData)
    fun deleteTransaction(transactionData: TransactionData) = db.dao().deleteTransaction(transactionData)

    suspend fun getTransaction(expenseId:Long): List<TransactionData> = db.dao().getTransaction(expenseId)
    suspend fun getTransactionAsTripID(tripId:Long): List<TransactionData> = db.dao().getTransactionAsTripId(tripId)
    suspend fun getUserOfExpense(userId:Long): List<User> = db.dao().getUserOfExpense(userId)

}