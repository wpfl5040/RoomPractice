package com.wpfl5.roompractice.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wpfl5.roompractice.db.entity.User

@Dao
interface UserDao {
    @Query("SELECT * from user ORDER BY id ASC")
    fun getUserList(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}