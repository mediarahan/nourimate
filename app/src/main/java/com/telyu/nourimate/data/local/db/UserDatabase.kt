package com.telyu.nourimate.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
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