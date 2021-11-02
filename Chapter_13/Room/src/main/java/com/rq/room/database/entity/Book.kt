package com.rq.room.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var naem: String, var pages: Int, var author: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}