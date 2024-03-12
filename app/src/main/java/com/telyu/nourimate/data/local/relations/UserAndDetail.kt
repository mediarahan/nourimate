package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.User

data class UserAndDetail(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val detail: Detail
)