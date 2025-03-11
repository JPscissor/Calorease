package ru.jpscissor.callorease.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.jpscissor.callorease.screens.GlobalParams

class JsonStorage(private val context: Context) {

    private val json = Json { prettyPrint = true } // Форматируем JSON для удобства чтения

    // Запись данных в файл
    suspend fun <T> writeToFile(filename: String, data: T, serializer: KSerializer<T>) {
        withContext(Dispatchers.IO) {
            context.openFileOutput(filename, Context.MODE_PRIVATE).use { stream ->
                stream.write(json.encodeToString(serializer, data).toByteArray())
            }
        }
    }

    // Чтение данных из файла
    suspend fun <T> readFromFile(filename: String, serializer: KSerializer<T>): T? {
        return withContext(Dispatchers.IO) {
            try {
                val jsonString = context.openFileInput(filename).bufferedReader().use { it.readText() }
                json.decodeFromString(serializer, jsonString)
            } catch (e: Exception) {
                null // Если файл не существует или данные повреждены
            }
        }
    }
}


fun saveProfileToJson(context: Context) {
    val profile = UserProfile(
        gender = GlobalParams.gender,
        age = GlobalParams.age,
        weight = GlobalParams.weight,
        height = GlobalParams.height,
        activityLevel = GlobalParams.activelvl
    )

    val json = Json { prettyPrint = true }
    val jsonString = json.encodeToString(profile)

    context.openFileOutput("profile.json", Context.MODE_PRIVATE).use { stream ->
        stream.write(jsonString.toByteArray())
    }
}


fun loadProfileFromJson(context: Context) {
    try {
        val jsonString = context.openFileInput("profile.json").bufferedReader().use { it.readText() }
        val profile = Json.decodeFromString<UserProfile>(jsonString)

        GlobalParams.gender = profile.gender
        GlobalParams.age = profile.age
        GlobalParams.weight = profile.weight
        GlobalParams.height = profile.height
        GlobalParams.activelvl = profile.activityLevel
    } catch (e: Exception) {
        // Файл не существует или данные повреждены
    }
}