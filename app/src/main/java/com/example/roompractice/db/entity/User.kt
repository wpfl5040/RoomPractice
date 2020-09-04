package com.wpfl5.roompractice.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var no: Int?,
    var id: String?,
    var name: String?
) {
    constructor(id: String, name: String) : this(null, id, name)
}