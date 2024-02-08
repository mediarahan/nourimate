package com.telyu.nourimate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.telyu.nourimate.data.local.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: User)

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("DELETE FROM users")
    suspend fun deleteAllRecords()
}