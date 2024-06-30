package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "profile_pictures")
@Parcelize
data class Profpic(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @field:ColumnInfo(name = "picture_url")
    val pictureUrl: String,

    @field:ColumnInfo(name = "user_id")
    val userId: Int?,

) : Parcelable