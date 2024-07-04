package com.telyu.nourimate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.SleepSegmentEventEntity
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import java.util.Date

@Dao
interface UserDao {

    //delete semuamuanya
    @Query("DELETE FROM userDetails")
    suspend fun deleteDetail()
    @Query("DELETE FROM history")
    suspend fun deleteHistories()

    @Query("DELETE FROM weight_entries")
    suspend fun deleteWeightEntries()

    @Query("DELETE FROM weight_tracks")
    suspend fun deleteWeightTrack()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(details: Detail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistories(histories: List<History>)


    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT name FROM users WHERE email = :email")
    suspend fun getUserNameByEmail(email: String): String?

    @Query("SELECT userId FROM users WHERE email = :email")
    suspend fun getUserIdByEmail(email: String): Int?

    @Query("SELECT * FROM userDetails WHERE userId = :id")
    suspend fun getUserDetailsById(id: Int): Detail?

    @Query("SELECT picture_url FROM profile_pictures WHERE user_id = :id")
    suspend fun getProfpicById(id: Int): String?

    @Query("SELECT bmi FROM userDetails WHERE userId = :id")
    suspend fun getBMIById(id: Int?): Float?

    @Update
    suspend fun updateUserProfile(detail: Detail)

    @Query("UPDATE users SET name = :name WHERE userId = :id")
    suspend fun updateUserName(id: Int, name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfpic(profpic: Profpic)

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

//    @Query("UPDATE users SET accountState = :state WHERE userId = :userId")
//    suspend fun updateAccountState(userId: Int, state: Int)

//    @Query("SELECT accountState FROM users WHERE userId = :userId")
//    suspend fun getAccountStateByUserId(userId: Int?): Int

    //WeightEntry / SpecialProgram related queries
    //for inserting currentWeight
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeightEntry(entry: WeightEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeightEntries(entries: List<WeightEntry>)

    //for deleting a particular user's weight entries. Used in SpecialProgram's Restore Program
    @Query("DELETE FROM weight_entries WHERE userId = :userId")
    suspend fun deleteWeightEntriesById(userId: Int)

    //for displaying weight entries from earliest to latest of a particular user
    // used in displaying progress graph
    @Query("SELECT *  FROM weight_entries WHERE userId = :userId ORDER BY date ASC")
    fun getWeightEntriesByUserIdAsc(userId: Int): LiveData<List<WeightEntry>>

    //for displaying latest weight entry of a particular user
    @Query("""
    SELECT * FROM weight_entries 
    WHERE date = (SELECT MAX(date) FROM weight_entries WHERE userId = :userId)
    AND userId = :userId
""")
    suspend fun getLatestWeightEntryByUserId(userId: Int): WeightEntry

    @Query("""
    SELECT * FROM weight_entries 
    WHERE date = (SELECT MAX(date) FROM weight_entries WHERE userId = :userId)
    AND userId = :userId
""")
    fun getLatestWeightEntryByUserId2(userId: Int): LiveData<WeightEntry>

    //for displaying latest weight entry date of a particular user
    @Query("SELECT MAX(date) FROM weight_entries WHERE userId = :userId")
    suspend fun getLatestWeightEntryDateByUserId(userId: Int): Date

    //for displaying earliest weight entry date of a particular user
    @Query("SELECT MIN(date) FROM weight_entries WHERE userId = :userId")
    suspend fun getEarliestWeightEntryDateByUserId(userId: Int): Date

    //getuserdetailbyid

    //update user's weight (after restoring program)
    @Query("UPDATE userDetails SET weight = :weight WHERE detailId = :detailId")
    suspend fun updateWeight(detailId: Int, weight: Int)

    @Query("SELECT * FROM weight_tracks  WHERE userId = :id")
    suspend fun getWeightTrackById(id: Int): WeightTrack?

    @Query("SELECT name FROM users WHERE userId = :id")
    suspend fun getUserNameById(id: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeightTrack(weightTrack: WeightTrack)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeightTracks(weightTracks: List<WeightTrack>)

    @Query("DELETE FROM weight_tracks WHERE userId = :userId")
    suspend fun deleteWeightTrackByUserId(userId: Int?)

    @Insert
    suspend fun insertHistory(history: History)

    @Query("SELECT * FROM history WHERE userId = :userId")
    suspend fun getHistory(userId: Int): List<History>

    @Query("SELECT * FROM weight_entries ORDER BY date ASC")
    fun getWeightEntriesLiveData(): LiveData<List<WeightEntry>>

    @Query("SELECT * FROM history WHERE userId = :userId ORDER BY createdAt DESC LIMIT 1")
    suspend fun getLatestHistory(userId: Int): History

    @Query("SELECT editCurrentWeightDate FROM weight_tracks WHERE userId = :userId")
    suspend fun getEditCurrentWeightDate(userId: Int): Date

    @Query("SELECT COUNT(*) FROM userDetails WHERE userId = :userId")
    suspend fun checkUserDetailExists(userId: Int): Int

    @Query("SELECT COUNT(*) FROM userDetails WHERE userId = :userId")
    suspend fun checkUserWeightTrackExists(userId: Int): Int

    @Query("SELECT COUNT(*) FROM userDetails WHERE userId = :userId")
    suspend fun checkUserMealHistoryExists(userId: Int): Int

    @Query("SELECT COUNT(*) FROM userDetails WHERE userId = :userId")
    suspend fun checkUserHistoryExists(userId: Int): Int


}