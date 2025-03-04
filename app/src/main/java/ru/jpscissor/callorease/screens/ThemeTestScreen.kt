package ru.jpscissor.callorease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.ui.theme.CallorEaseTheme

@Composable
fun ThemeTestScreen( onBack: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ){

        Column (
            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)
                .padding()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
            ) {
                Text(
                    "ТЕКСТ ДЛЯ ПРОВЕРКИ",
                    modifier = Modifier.padding(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold

                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(vertical = 32.dp)
            ) { Text("проверка цвета") }

            Row(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onSurface).padding(vertical = 32.dp)
            ) { Text("проверка цвета") }

            Row(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant).padding(vertical = 32.dp)
            ) { Text("проверка цвета") }

            Row(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onSurfaceVariant).padding(vertical = 32.dp)
            ) { Text("проверка цвета") }

            Button(
                onClick = { onBack() },
                modifier = Modifier.height(50.dp).width(250.dp),
                colors = ButtonColors(
                    containerColor = Color.White,
                    disabledContentColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Black
                ),
                shape = RoundedCornerShape(15.dp),
            ) {
                Text("Изменить тему", fontSize = 20.sp,
                    color = Color.Black
                )
            }

        }

    }


}

@Composable
@Preview
fun PrevTTScreen() {
    CallorEaseTheme {
        ThemeTestScreen(onBack = {})
    }
}