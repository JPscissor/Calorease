package ru.jpscissor.callorease.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.LocalTime


@Serializable
data class ConsumedProduct(
    val productName: String,
    val calories: Double,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val grams: Int,
    val consumedDate: String,
    val consumedTime: String
)


fun addConsumedProduct(
    context: Context,
    name: String,
    calories: Double,
    proteins: Double,
    fats: Double,
    carbs: Double,
    grams: Int
) {
    val consumedProducts = loadConsumedProductsFromFile(context, "consumed.json").toMutableList()

    val currentTime = LocalTime.now().toString()

    val consumedProduct = ConsumedProduct(
        productName = name,
        calories = calories * grams / 100,
        proteins = proteins * grams / 100,
        fats = fats * grams / 100,
        carbohydrates = carbs * grams / 100,
        grams = grams,
        consumedDate = LocalDate.now().toString(),
        consumedTime = currentTime
    )

    Log.d("AddProduct", "Added product: ${consumedProduct.productName}, Time: ${consumedProduct.consumedTime}")

    consumedProducts.add(0, consumedProduct)

    saveConsumedProductsToFile(context, "consumed.json", consumedProducts)
}



fun saveConsumedProductsToFile(context: Context, filename: String, consumedProducts: List<ConsumedProduct>) {
    try {
        Log.d("FileSave", "Saving ${consumedProducts.size} products to $filename")
        Log.d("FileSave", "Products with dates: ${consumedProducts.map { it.consumedDate }}")

        val jsonString = Json.encodeToString(consumedProducts)
        context.openFileOutput(filename, Context.MODE_PRIVATE).use { it.write(jsonString.toByteArray()) }
    } catch (e: Exception) {
        Log.e("FileSave", "Error saving file", e)
    }
}


fun loadConsumedProductsFromFile(context: Context, filename: String): List<ConsumedProduct> {
    return try {
        val jsonString = context.openFileInput(filename).bufferedReader().use { it.readText() }
        val products = Json.decodeFromString<List<ConsumedProduct>>(jsonString)

        products.mapNotNull { product ->
            if (product.consumedTime.isEmpty()) {
                Log.e("FileLoad", "Missing consumedTime for product: ${product.productName}")
                null
            } else {
                product
            }
        }
    } catch (e: Exception) {
        Log.e("FileLoad", "Error loading file", e)
        emptyList()
    }
}


fun fileExistsInInternalStorage(context: Context, filename: String): Boolean {
    return try {
        val file = context.getFileStreamPath(filename)
        file.exists()
    } catch (e: Exception) {
        Log.e("FileCheck", "Error checking file existence", e)
        false
    }
}


fun initializeConsumedProductsFile(context: Context, filename: String) {
    Log.d("FileInit", "Initializing file: $filename")
    try {
        if (!fileExistsInInternalStorage(context, filename)) {
            Log.d("FileInit", "File does not exist. Creating a new one.")
            val initialData = emptyList<ConsumedProduct>()
            saveConsumedProductsToFile(context, filename, initialData)
        } else {
            Log.d("FileInit", "File already exists.")
        }
    } catch (e: Exception) {
        Log.e("FileInit", "Error initializing file", e)
    }
}


fun calculateTotals(context: Context, filename: String): Map<String, Double> {
    val today = LocalDate.now().toString()
    val consumedProducts = loadConsumedProductsFromFile(context, filename)
        .filter { it.consumedDate == today }

    val totalCalories = consumedProducts.sumOf { it.calories }
    val totalProteins = consumedProducts.sumOf { it.proteins }
    val totalCarbohydrates = consumedProducts.sumOf { it.carbohydrates }
    val totalFats = consumedProducts.sumOf { it.fats }

    return mapOf(
        "calories" to totalCalories,
        "proteins" to totalProteins,
        "carbohydrates" to totalCarbohydrates,
        "fats" to totalFats
    )
}


fun loadAndCalculateTotals(context: Context, filename: String): Map<String, Double> {
    return calculateTotals(context, filename)
}


fun migrateOldData(context: Context, filename: String) {
    val consumedProducts = loadConsumedProductsFromFile(context, filename).toMutableList()

    Log.d("MigrateOldData", "Loaded products: ${consumedProducts.map { it.consumedDate }}")

    val updatedProducts = consumedProducts.map { product ->
        if (product.consumedDate.isEmpty()) {
            val updatedProduct = product.copy(consumedDate = LocalDate.now().toString())
            Log.d("MigrateOldData", "Updated product: ${updatedProduct.productName}, New Date: ${updatedProduct.consumedDate}")
            updatedProduct
        } else {
            product
        }
    }

    saveConsumedProductsToFile(context, filename, updatedProducts)
}


fun getLast10Products(context: Context, filename: String): List<ConsumedProduct> {
    val allProducts = loadConsumedProductsFromFile(context, filename)

    Log.d("RecentProducts", "Total products: ${allProducts.size}")

    val uniqueProducts = allProducts
        .reversed()
        .distinctBy { it.productName }
        .reversed()

    return uniqueProducts.takeLast(10)
}


fun getConsumedProductsByTimeRange(
    context: Context,
    filename: String,
    startTime: String,
    endTime: String,
    consumedDate: String
): List<ConsumedProduct> {
    val allProducts = loadConsumedProductsFromFile(context, filename)
    return allProducts.filter { product ->
        try {
            if (product.consumedDate != consumedDate) {
                return@filter false
            }

            val productTime = LocalTime.parse(product.consumedTime)
            val start = LocalTime.parse(startTime)
            val end = LocalTime.parse(endTime)
            !productTime.isBefore(start) && !productTime.isAfter(end)
        } catch (e: Exception) {
            Log.e("FilterError", "Invalid time format for product: ${product.productName}")
            false
        }
    }
}