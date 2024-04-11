package com.example.spliteasy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spliteasy.db.model.ExpenseData
import com.example.spliteasy.db.model.TransactionData
import com.example.spliteasy.db.model.Trip
import com.example.spliteasy.db.model.TripUser
import com.example.spliteasy.db.model.User

@Database(entities = [User::class, Trip::class, TripUser::class,ExpenseData::class,TransactionData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        private const val DB_NAME = "split.db"
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun initDB(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDB(context).also {
                instance = it
            }
        }

        fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java,
            DB_NAME
        ).allowMainThreadQueries().build()
    }
}