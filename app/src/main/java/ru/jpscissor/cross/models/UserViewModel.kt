package ru.jpscissor.cross.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import ru.jpscissor.cross.database.AppDatabase
import ru.jpscissor.cross.database.UserData

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val userDao = database.userDao()

    val user = userDao.getUser().asLiveData()

    fun insertUser(weight: Int, height: Int, age: Int, gender: Int, activityLevel: Int) {
        viewModelScope.launch {
            val userData = UserData(weight = weight, height = height, age = age, gender = gender, activityLevel = activityLevel)
            userDao.insert(userData)
        }
    }

    val allUsers = userDao.getAllUsers().asLiveData()
}