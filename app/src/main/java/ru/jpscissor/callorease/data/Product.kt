package ru.jpscissor.callorease.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Product(
    val name: String,
    val calories: Double,
    val proteins: Double,
    val carbs: Double,
    val fats: Double
)


fun loadProductsFromAssets(context: Context, filePath: String): List<Product> {
    return try {
        val jsonString = context.assets.open(filePath).bufferedReader().use { it.readText() }
        println("Loaded JSON from path: $filePath")
        Json.decodeFromString<List<Product>>(jsonString)
    } catch (e: Exception) {
        e.printStackTrace()
        println("Error loading JSON from path: $filePath - ${e.message}")
        emptyList()
    }
}

fun searchProducts(query: String, products: List<Product>): List<Product> {
    return products.filter { it.name.contains(query, ignoreCase = true) }
}

fun saveConsumedProductsToJson(consumedProducts: List<ConsumedProduct>): String {
    return Json.encodeToString(consumedProducts)
}

fun loadConsumedProductsFromJson(jsonString: String): List<ConsumedProduct> {
    return Json.decodeFromString<List<ConsumedProduct>>(jsonString)
}