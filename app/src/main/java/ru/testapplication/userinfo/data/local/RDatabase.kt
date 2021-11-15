package ru.testapplication.userinfo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomUser::class], version = 1)
abstract class RDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    companion object {
        private const val DB_NAME = "database.db"
        private var instance: RoomDatabase? = null

        fun getInstance() = instance ?: throw IllegalStateException("DB absent")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    RDatabase::class.java,
                    DB_NAME
                )
                    .build()
            }
        }
    }
}