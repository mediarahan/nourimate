package com.telyu.nourimate.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.utils.Converters

@Database(entities = [User::class,Detail::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        @JvmStatic
        fun getInstance(context: Context): UserDatabase {
            return if (INSTANCE != null) {
                INSTANCE as UserDatabase
            } else {
                val instance: UserDatabase =
                    synchronized(UserDatabase::class.java) {
                        androidx.room.Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            "user"
                        )
                            .build()
                    }
                INSTANCE = instance
                instance
            }
        }
    }

}