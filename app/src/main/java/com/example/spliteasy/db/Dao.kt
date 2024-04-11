package com.example.spliteasy.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.spliteasy.db.model.ExpenseData
import com.example.spliteasy.db.model.ExpenseWithUser
import com.example.spliteasy.db.model.TransactionData
import com.example.spliteasy.db.model.Trip
import com.example.spliteasy.db.model.TripUser
import com.example.spliteasy.db.model.User

@Dao
interface Dao {
    @Insert(entity = User::class)
    fun addUser(user: User)

    @Update(entity = User::class)
    fun updateUser(user: User)

    @Update(entity = User::class)
    fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * FROM User WHERE user_id =:userIds")
    fun getMultipleUsers(userIds:Int): User

    @Insert(entity = Trip::class)
    fun addTrip(trip: Trip): Long

    @Update(entity = Trip::class)
    fun updateTrip(trip: Trip)

    @Update(entity = Trip::class)
    fun deleteTrip(trip: Trip)

    @Query("SELECT * FROM trip")
    fun getTrips(): List<Trip>

    @Insert(entity = TripUser::class)
    fun addTripUsers(tripUser: List<TripUser>)

    @Update(entity = TripUser::class)
    fun updateTripUsers(tripUser: TripUser)

    @Update(entity = TripUser::class)
    fun deleteTripUsers(tripUser: TripUser)

    @Query("SELECT * FROM tripusers")
    fun getTripsUser(): List<TripUser>

    @Transaction
    @Query("SELECT * FROM user u INNER JOIN tripusers tu ON u.user_id = tu.user_id WHERE tu.trip_id = :tripId")
    suspend fun getTripWithUsers(tripId: Long): List<User>


    //Expense
    @Insert(entity = ExpenseData::class)
    fun addExpense(expense: ExpenseData): Long

    @Update(entity = ExpenseData::class)
    fun updateExpense(expense: ExpenseData)

    @Update(entity = ExpenseData::class)
    fun deleteExpense(expense: ExpenseData)

    @Query("SELECT * FROM GroupExpense WHERE group_id = :tripId")
    fun getExpense(tripId: Long): List<ExpenseData>

    //Add Transaction
    @Insert(entity = TransactionData::class)
    fun addTransaction(expense: List<TransactionData>)

    @Update(entity = TransactionData::class)
    fun updateTransaction(expense: List<TransactionData>)

    @Update(entity = TransactionData::class)
    fun deleteTransaction(expense: TransactionData)

    @Query("SELECT * FROM ExpenseTransaction WHERE expense_id = :expenseId")
    fun getTransaction(expenseId: Long): List<TransactionData>

    @Query("SELECT * FROM ExpenseTransaction WHERE group_id = :tripId")
    fun getTransactionAsTripId(tripId: Long): List<TransactionData>

    @Transaction
    @Query("SELECT * FROM User WHERE user_id = :userId")
    fun getUserOfExpense(userId: Long) : List<User>


}