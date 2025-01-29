package ru.jpscissor.cross.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weight: Int,
    val height: Int,
    val age: Int,
    val gender: Int,
    val activityLevel: Int
)