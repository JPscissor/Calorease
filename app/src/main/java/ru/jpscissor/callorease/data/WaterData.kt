package ru.jpscissor.callorease.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.jpscissor.callorease.screens.GlobalProgress
import java.time.LocalDate

@Serializable
data class WaterData(
    val consumedDate: String,
    val waterAmount: Float = 0.0F
)


fun saveWaterToFile(context: Context, filename: String, waterData: WaterData) {
    try {
        val jsonString = Json.encodeToString(waterData)
        context.openFileOutput(filename, Context.MODE_PRIVATE).use { it.write(jsonString.toByteArray()) }
        Log.d("FileSave", "Saved water data: $jsonString")
    } catch (e: Exception) {
        Log.e("FileSave", "Error saving water data", e)
    }
}


fun loadWaterFromFile(context: Context, filename: String): WaterData {
    return try {
        val jsonString = context.openFileInput(filename).bufferedReader().use { it.readText() }
        Log.d("FileLoad", "Loaded JSON: $jsonString")
        Json.decodeFromString<WaterData>(jsonString)
    } catch (e: Exception) {
        Log.e("FileLoad", "Error loading file", e)
        
        WaterData(consumedDate = LocalDate.now().toString(), waterAmount = 0.0F)
    }
}


fun clearOldWater(context: Context, filename: String) {
    val today = LocalDate.now().toString()
    val waterData = loadWaterFromFile(context, filename)

    Log.d("ClearOldWater", "Today's date: $today")
    Log.d("ClearOldWater", "Loaded water data: ${waterData.consumedDate}, Water amount: ${waterData.waterAmount}")

    if (waterData.consumedDate != today) {
        Log.d("ClearOldWater", "Resetting water counter for new day")

        val updatedData = WaterData(
            consumedDate = today,
            waterAmount = 0.0F
        )
        saveWaterToFile(context, filename, updatedData)

        GlobalProgress.todayWater = 0.0F
    } else {
        Log.d("ClearOldWater", "Water data is up-to-date")
        GlobalProgress.todayWater = waterData.waterAmount
    }
}


fun migrateOldWaterData(context: Context, filename: String) {
    try {
        val jsonString = context.openFileInput(filename).bufferedReader().use { it.readText() }
        Log.d("MigrateWater", "Loaded old water data: $jsonString")

        val oldData = Json.decodeFromString<WaterData>(jsonString)

        if (oldData.consumedDate.isEmpty()) {
            val updatedData = oldData.copy(consumedDate = LocalDate.now().toString())
            saveWaterToFile(context, filename, updatedData)
            Log.d("MigrateWater", "Updated water data with date: ${updatedData.consumedDate}")
        } else {
            Log.d("MigrateWater", "Water data already contains date")
        }
    } catch (e: Exception) {
        Log.e("MigrateWater", "Error migrating water data", e)
    }
}


fun initializeWaterFile(context: Context, filename: String) {
    Log.d("FileInit", "Initializing file: $filename")
    try {
        if (!fileExistsInInternalStorage(context, filename)) {
            Log.d("FileInit", "File does not exist. Creating a new one.")
            val initialData = WaterData(
                consumedDate = LocalDate.now().toString(),
                waterAmount = 0.0F
            )
            saveWaterToFile(context, filename, initialData)
        } else {
            Log.d("FileInit", "File already exists.")
        }
    } catch (e: Exception) {
        Log.e("FileInit", "Error initializing file", e)
    }
}