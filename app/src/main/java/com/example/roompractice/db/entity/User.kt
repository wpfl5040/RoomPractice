package com.example.roompractice.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var id: Int,
    var firstName: String?,
    var lastName: String?
)