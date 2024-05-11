package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users",
//    foreignKeys = [
//        ForeignKey(
//            entity = Detail::class,
//            parentColumns = ["detailId"],
//            childColumns = ["detailId"],
//            onDelete = ForeignKey.CASCADE
//        ),
//    ]
)
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val name: String,
    val email: String,
    val phoneNumber: Long = 0,
    val password: String,
    val accountState: Int = 0,

) : Parcelable

