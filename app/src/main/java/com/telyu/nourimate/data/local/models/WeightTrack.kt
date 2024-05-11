package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "weight_tracks")
@Parcelize
data class WeightTrack(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ongoingProgram: Int = 0,
    val startDate: Date?,
    val endDate: Date?,
    val startWeight: Int,
    val endWeight: Int,
    val editCurrentWeightDate: Date,
    val userId: Int = 0,

    ): Parcelable

//0 = inactive
//1 = maintain weight
//2 = maintain health
//3 = lose weight
//4 = gain weight
