package com.telyu.nourimate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.SleepSegmentEventEntity
import com.telyu.nourimate.data.local.models.User
import java.util.Date

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(details: Detail)

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT name FROM users WHERE email = :email")
    suspend fun getUserNameByEmail(email: String): String?

    @Query("SELECT userId FROM users WHERE email = :email")
    suspend fun getUserIdByEmail(email: String): Int?

    @Query("SELECT * FROM userDetails WHERE detailId = :id")
    suspend fun getUserDetailsById(id: Int): Detail?

    @Query("SELECT bmi FROM userDetails WHERE detailId = :id")
    suspend fun getBMIById(id: Int?): Int?

    @Update
    suspend fun updateUserProfile(detail: Detail)

    @Query("UPDATE users SET name = :name WHERE userId = :id")
    suspend fun updateUserName(id: Int, name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfpic(profpic: Profpic)

    @Query("SELECT picture_url FROM profile_pictures WHERE id = :id")
    suspend fun getProfpicById(id: Int): String?

    @Query("DELETE FROM users")
    suspend fun deleteAllRecords()

    @Query(
        """
        SELECT * FROM userDetails
        INNER JOIN users ON userDetails.detailId = users.userId
        WHERE users.email = :email
    """
    )
    suspend fun getUserDetailsByEmail(email: String): Detail

    //Sleep API Related
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepSegment(segment: SleepSegmentEventEntity)

    @Query("SELECT * FROM sleep_segments")
    fun getAllSleepSegments(): LiveData<List<SleepSegmentEventEntity>>

    @Query("UPDATE users SET accountState = :state WHERE userId = :userId")
    suspend fun updateAccountState(userId: Int, state: Int)

    @Query("SELECT accountState FROM users WHERE userId = :userId")
    suspend fun getAccountStateByUserId(userId: Int?): Int

}