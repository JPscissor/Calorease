package ru.jpscissor.callorease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.navigation.NavRoute
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper

@Composable
fun OnboardScreen(onNavigateToSelect: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ONBOARD")

        Button(
            onClick = {onNavigateToSelect()}
        ) {
            Text("Далее", color = MaterialTheme.colorScheme.tertiary)
        }
    }

}

@Composable
@Preview
fun PrevOnboardScreen() {

    val navController = rememberNavController()

    AppThemeWrapper {
        OnboardScreen(onNavigateToSelect = { navController.navigate(NavRoute.ThemeSelection.route) })
    }
}