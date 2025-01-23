package ru.jpscissor.cross.screens.loging

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ru.jpscissor.cross.ui.theme.CrossTheme
import kotlin.math.abs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "InvalidColorHexValue")
@Composable
fun LogscScreen(navController: NavHostController) {

    var checkVal = 0
    var selectedIndex by remember { mutableIntStateOf(0) }
    var selectedGender by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1919)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 25.dp)) {
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
                    text = "Добро Пожаловать!",
                    color = Color(0xFF79FF57),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Введите свои параметры в поля ниже",
                    color = Color(0xFF79FF57),
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
                        initialValue = 0,
                        onValueChange = { checkVal++ }
                    )

                    ParameterInput(
                        label = "Рост",
                        minValue = 130,
                        maxValue = 220,
                        initialValue = 0,
                        onValueChange = { checkVal++ }
                    )

                    ParameterInput(
                        label = "Возраст",
                        minValue = 16,
                        maxValue = 99,
                        initialValue = 0,
                        onValueChange = { checkVal++ }
                    )

                    Text(
                        text = "Пол",
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 0.dp)
                            .fillMaxWidth(),
                        fontSize = 18.sp,
                        color = Color(0xFF79FF57)
                    )

                    GenderSelector(
                        selectedGender = selectedGender,
                        onValueChange = { selectedGender = it; checkVal++ }
                    )

                    Text(
                        text = "Уровень активности",
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 0.dp)
                            .fillMaxWidth(),
                        fontSize = 18.sp,
                        color = Color(0xFF79FF57)
                    )

                    ActivitySelector(
                        selectedIndex = selectedIndex,
                        onValueChange = { selectedIndex = it; checkVal++; }
                    )
                }
            }

            Button(
                onClick = { if (checkVal >= 5) navController.navigate(NavRoute.Homesc.route)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (checkVal >= 5) Color(0xFF79FF57) else Color(0xF79FF57)
                )
            ) {
                Text("Далее", fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun ParameterInput(label: String, minValue: Int, maxValue: Int, initialValue: Int, onValueChange: (Int) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            color = Color(0xFF79FF57),
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalValuePicker(
            minValue = minValue,
            maxValue = maxValue,
            initialValue = initialValue,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun HorizontalValuePicker(minValue: Int, maxValue: Int, initialValue: Int, onValueChange: (Int) -> Unit) {
    var selectedValue by remember { mutableIntStateOf(initialValue) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items((minValue..maxValue).toList()) { value ->
            val alpha = 1f - (0.1f * abs(value - selectedValue))
            val scale = if (value == selectedValue) 1.2f else 1f

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value.toString(),
                    fontSize = (20f * scale).coerceAtLeast(10f).sp,
                    fontWeight = if (value == selectedValue) FontWeight.ExtraBold else FontWeight.Normal,
                    color = if (value == selectedValue) Color.DarkGray else Color.Gray.copy(alpha = alpha.coerceAtLeast(0.5f)),
                    modifier = Modifier
                        .background(
                            color = if (selectedValue == value) Color(0xFF79FF57) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp))
                        .clickable {
                            selectedValue = value
                            onValueChange(value)
                        }
                )
            }
        }
    }
}

@Composable
fun ActivitySelector(selectedIndex: Int, onValueChange: (Int) -> Unit) {
    val labels = listOf("Низкая", "Умеренная", "Высокая")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(labels.size) { index ->
            Text(
                text = labels[index],
                fontWeight = if (selectedIndex == index) FontWeight.Bold else FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier
                    .clickable { onValueChange(index) }
                    .padding(8.dp)
                    .background(
                        color = if (selectedIndex == index) Color(0xFF79FF57) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}


@Composable
fun GenderSelector(selectedGender: Int, onValueChange: (Int) -> Unit) {
    val labels = listOf("Мужской", "Женский")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(labels.size) { index ->
            Text(
                text = labels[index],
                fontWeight = if (selectedGender == index) FontWeight.Bold else FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier
                    .clickable { onValueChange(index) }
                    .padding(8.dp)
                    .background(
                        color = if (selectedGender == index) Color(0xFF79FF57) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevLogscScreen() {
    CrossTheme {
        LogscScreen(navController = rememberNavController())
    }
}