package com.example.roompractice.repository

import androidx.lifecycle.LiveData
import com.wpfl5.roompractice.db.dao.UserDao
import com.wpfl5.roompractice.db.entity.User

class UserRepository (
    private val userDao: UserDao
){
    val userList: LiveData<List<User>> = userDao.getUserList()

     fun insert(user: User) = userDao.insertUser(user)

     fun deleteAllUser(){
        userDao.deleteAll()
    }
}