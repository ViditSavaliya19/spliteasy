package com.example.spliteasy.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.Transaction

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) var user_id: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val phone: String,
    @ColumnInfo val email: String
)

@Entity(tableName = "trip")
data class Trip(
    @PrimaryKey(autoGenerate = true) var trip_id: Int = 0,
    @ColumnInfo val name: String,
)

@Entity(
    tableName = "tripusers",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Trip::class,
            parentColumns = ["trip_id"],
            childColumns = ["trip_id"],
            onDelete = ForeignKey.CASCADE
        ),

    ]
)
data class TripUser(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo var user_id: Int,
    @ColumnInfo var trip_id: Int,
)

@Entity(
    tableName = "GroupExpense", foreignKeys = [
        ForeignKey(
            entity = Trip::class,
            parentColumns = ["trip_id"],
            childColumns = ["group_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["paidByUserId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class ExpenseData(
    @PrimaryKey(autoGenerate = true) var expense_id: Int = 0,
    @ColumnInfo var totalAmount: Int,
    @ColumnInfo var remark: String,
    @ColumnInfo var paidByUserId: Int,
    @ColumnInfo var group_id: Int,
)

@Entity(tableName = "ExpenseTransaction", foreignKeys = [
    ForeignKey(
        entity = Trip::class,
        parentColumns = ["trip_id"],
        childColumns = ["group_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = ExpenseData::class,
        parentColumns = ["expense_id"],
        childColumns = ["expense_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    ),
])
data class TransactionData(
    @PrimaryKey(autoGenerate = true) var transaction_id: Int = 0,
    @ColumnInfo var group_id: Int,
    @ColumnInfo var expense_id: Int,
    @ColumnInfo var user_id: Int,
    @ColumnInfo var amount: Double,
)


data class ExpenseWithUser(
    @Embedded val expense: ExpenseData,
    @Embedded val user: User,
//    @Embedded val transactionDataList: TransactionData

)

//data class TripWithUsers(
//    @Embedded val tripUser: TripUser,
//    @Relation(
//        parentColumn = "user_id",
//        entityColumn = "user_id"
//    )
//    val users: List<User>
//)



