package ru.jpscissor.callorease.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InputViewModel(application: Application) : AndroidViewModel(application) {
    private val storage = JsonStorage(application)
    private val _profile = MutableStateFlow(UserProfile())
    val profile: StateFlow<UserProfile> = _profile

    fun updateProfileField(field: String, value: Int) {
        _profile.value = when (field) {
            "gender" -> _profile.value.copy(gender = value)
            "age" -> _profile.value.copy(age = value)
            "weight" -> _profile.value.copy(weight = value)
            "height" -> _profile.value.copy(height = value)
            "activityLevel" -> _profile.value.copy(activityLevel = value)
            else -> _profile.value
        }
    }


    fun saveProfile() {
        viewModelScope.launch {
            storage.writeToFile("profile.json", _profile.value, UserProfile.serializer())
        }
    }


    init {
        viewModelScope.launch {
            _profile.value = storage.readFromFile("profile.json", UserProfile.serializer()) ?: _profile.value
        }
    }
}