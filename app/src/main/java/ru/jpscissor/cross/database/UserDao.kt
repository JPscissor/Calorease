package ru.jpscissor.cross.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserData)

    @Query("SELECT * FROM user_data ORDER BY id DESC LIMIT 1")
    fun getUser(): Flow<UserData?>

    @Query("SELECT * FROM user_data")
    fun getAllUsers(): Flow<List<UserData>>
}