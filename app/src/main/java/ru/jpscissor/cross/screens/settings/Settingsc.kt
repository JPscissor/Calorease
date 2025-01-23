package ru.jpscissor.cross.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.cross.navigation.NavRoute
import ru.jpscissor.cross.screens.loging.ActivitySelector
import ru.jpscissor.cross.ui.theme.CrossTheme
import ru.jpscissor.cross.screens.loging.ParameterInput

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingscScreen(navController: NavHostController) {

    var checkVal = 1
    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1919)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = "Редактирование Данных",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "здесь вы можете изменить введеную ранее информацию",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 40.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )

                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ParameterInput(
                        label = "Вес",
                        minValue = 30,
                        maxValue = 150,
                        initialValue = 70,
                        onValueChange = { checkVal++ }
                    )

                    ParameterInput(
                        label = "Рост",
                        minValue = 130,
                        maxValue = 220,
                        initialValue = 170,
                        onValueChange = { checkVal++ }
                    )

                    ParameterInput(
                        label = "Возраст",
                        minValue = 16,
                        maxValue = 99,
                        initialValue = 25,
                        onValueChange = { checkVal++ }
                    )

                    Text(
                        text = "Уровень активности",
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 0.dp)
                            .fillMaxWidth(),
                        fontSize = 18.sp,
                        color = Color.White
                    )

                    ActivitySelector(
                        selectedIndex = selectedIndex,
                        onValueChange = { selectedIndex = it }
                    )
                }
            }

            Button(
                onClick = { if (checkVal >= 3) navController.navigate(NavRoute.Homesc.route)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text("Готово", fontSize = 18.sp,
                    color = Color(0xFF000000)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevSettingscScreen() {
    CrossTheme {
        SettingscScreen(navController = rememberNavController())
    }
}