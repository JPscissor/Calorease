package ru.jpscissor.callorease.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.jpscissor.callorease.data.InputViewModel

@Composable
fun ProfileScreen(viewModel: InputViewModel, onNavigateToThemeTest: () -> Unit) {
    val profile by viewModel.profile.collectAsState()

    Column(
        Modifier.fillMaxSize().padding(32.dp)
    ) {

        Text( text = "Вес: ${profile.weight}", color = MaterialTheme.colorScheme.tertiary )
        Text( text = "Рост: ${profile.height}", color = MaterialTheme.colorScheme.tertiary )
        Text( text = "Возраст: ${profile.age}", color = MaterialTheme.colorScheme.tertiary )
        Text( text = "Пол: ${if (profile.gender == 0) "НИЗКИЙ" else if (profile.gender == 1) "УМЕРЕННЫЙ" else "ВЫСОКИЙ"}",
            color = MaterialTheme.colorScheme.tertiary )
        Text( text = "Пол: ${if (profile.gender == 0) "Мужской" else "Женский"}", color = MaterialTheme.colorScheme.tertiary)

    }

}