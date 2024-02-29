package com.telyu.nourimate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail (details: Detail)

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT name FROM users WHERE email = :email")
    suspend fun getUserNameByEmail(email:String): String?

    @Query("SELECT id FROM users WHERE email = :email")
    suspend fun getUserIdByEmail(email: String): Int?

    @Query("SELECT * FROM userDetails WHERE id = :id")
    suspend fun getUserDetailsById (id: Int): Detail?

    @Query("SELECT bmi FROM userDetails WHERE id = :id")
    suspend fun getBMIById(id: kotlin.Int?): Int?

    @Query("DELETE FROM users")
    suspend fun deleteAllRecords()

}