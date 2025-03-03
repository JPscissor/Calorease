package ru.jpscissor.callorease.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.ThemeManager

@Composable
fun ThemeSelectionScreen(themeManager: ThemeManager, onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp,  vertical = 32.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Выберете цветовую схему",
                    modifier = Modifier,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Text(
                    text = "Выбирайте наиболее приятные для вас цвета",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 64.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                DefaultCard(painterResource(R.drawable.chbth), painterResource(R.drawable.mnex))

                Spacer(Modifier.height(24.dp))

                DefaultCard(painterResource(R.drawable.dkth), painterResource(R.drawable.dkex))

                Spacer(Modifier.height(24.dp))

                DefaultCard(painterResource(R.drawable.ltth), painterResource(R.drawable.ltex))
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {},
                modifier = Modifier.height(50.dp).width(130.dp).align(Alignment.End),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    disabledContentColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Black
                ),
                shape = RoundedCornerShape(15.dp),
            ) {
                Text("Далее", fontSize = 20.sp, color = MaterialTheme.colorScheme.tertiary)
            }

        }
    }

}


@Composable
fun DefaultCard(imgRes: Painter, imgRes2: Painter) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffE4E4E4)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Image(
                painter = imgRes,
                contentDescription = "Image description"
            )

            Spacer(Modifier.weight(1f))

            Image(
                painter = imgRes2,
                contentDescription = ""
            )
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