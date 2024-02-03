package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity (tableName = "userDetails")
@Parcelize
data class Detail (
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "email")
    val email: String,

    @field:ColumnInfo(name = "phone_number")
    val phoneNumber: Int = 0,

    @field:ColumnInfo(name = "password")
    val password: String,
): Parcelable