package com.example.roompractice.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roompractice.db.AppDatabase
import com.example.roompractice.repository.UserRepository
import com.wpfl5.roompractice.db.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
    val userDao = AppDatabase.getInstance(application, viewModelScope).userDao()
    private val repository: UserRepository = UserRepository(userDao)

    val allUsers: LiveData<List<User>> = repository.userList

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(user)
    }

    fun deleteAllUser() = viewModelScope.launch(Dispatchers.IO) { repository.deleteAllUser() }
}