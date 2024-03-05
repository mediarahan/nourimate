package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.User

data class UserAndProfpic(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val profpic: Profpic
)