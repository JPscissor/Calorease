package ru.jpscissor.callorease.data
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val gender: Int = 0,
    val age: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    val activityLevel: Int = 0
)
