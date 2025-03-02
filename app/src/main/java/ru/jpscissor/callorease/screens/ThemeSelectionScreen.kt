package ru.jpscissor.callorease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.ThemeManager
import ru.jpscissor.callorease.ui.theme.Typography

@Composable
fun ThemeSelectionScreen(themeManager: ThemeManager, onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF4F4F4))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp,  vertical = 32.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Выберете цветовую схему",
                    modifier = Modifier,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Выбирайте наиболее приятные для вас цвета",
                    fontSize = 20.sp
                )
            }

        }
    }

}

@Composable
@Preview
fun PrevTSScreen() {
    AppThemeWrapper {
        val themeManager = ThemeManager(LocalContext.current)
        ThemeSelectionScreen(
            themeManager = themeManager,
            onBack = {  }
        )
    }
}


//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(onClick = {
//            themeManager.selectedTheme = AppTheme.Light
//            onBack()
//        }) {
//            Text("Выбрать светлую тему")
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            themeManager.selectedTheme = AppTheme.Dark
//            onBack()
//        }) {
//            Text("Выбрать тёмную тему")
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            themeManager.selectedTheme = AppTheme.Monochrome
//            onBack()
//        }) {
//            Text("Выбрать монохромную тему")
//        }
//    }