package com.example.roompractice.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wpfl5.roompractice.db.dao.UserDao
import com.wpfl5.roompractice.db.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }

        }

        private fun roomCallback(
             scope: CoroutineScope
        ) = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let {
                    scope.launch(Dispatchers.IO) {
                        initDatabase(it.userDao())
                        Log.d("//test", it.userDao().getUserList().toString())
                    }
                }
            }
        }


        private fun initDatabase(userDao: UserDao){
            //초깃값 셋팅
            //userDao.deleteAll()

            userDao.insertUser(User("wpfl", "현진"))
            userDao.insertUser(User("wpfl2", "현진2"))
        }



    }
}