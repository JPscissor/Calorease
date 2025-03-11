package ru.jpscissor.callorease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.navigation.NavRoute
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper

@Composable
fun OnboardScreen(onNavigateToSelect: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xffF4F4F4)).padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(128.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Добро\nпожаловать в",
                fontWeight = FontWeight.Medium,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = "Callorease!",
                color = Color(0xffBDF168),
                fontSize = 36.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { onNavigateToSelect() },
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Начать",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
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