package ru.jpscissor.callorease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.jpscissor.callorease.ui.theme.CallorEaseTheme

@Composable
fun ThemeTestScreen() {

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onBackground)
        ){
            Text(
                text = "ТЕКСТ ДЛЯ ПРОВЕРКИ",
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

}

@Composable
@Preview
fun PrevTTScreen() {
    CallorEaseTheme {
        ThemeTestScreen()
    }
}