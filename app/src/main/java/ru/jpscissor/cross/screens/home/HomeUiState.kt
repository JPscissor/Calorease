package ru.jpscissor.cross.screens.home

data class HomeUiState(
    val calNorm: Int = 0,
    val proteins: Int = 0,
    val carbs: Int = 0,
    val fats: Int = 0,

    val proteinsNorm: Int = 0,
    val carbsNorm: Int = 0,
    val fatsNorm: Int =  0
)
