package ru.jpscissor.callorease.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.screens.GlobalParams.activelvl
import ru.jpscissor.callorease.screens.GlobalParams.gender
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper


object GlobalParams {
    var gender by mutableIntStateOf(0)
    var activelvl by mutableIntStateOf(0)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "InvalidColorHexValue")
@Composable
fun InputScreen(onNavigateToHome: () -> Unit) {

    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // Фон экрана
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            // Заголовок
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Укажите параметры",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Text(
                    text = "Точные данные - залог\nточных расчетов",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Вес
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Вес",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(65.dp))

                CustomInputField(
                    value = weight,
                    onValueChange = { newValue ->
                        weight = newValue
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "кг",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Рост
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Рост",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(65.dp))

                CustomInputField(
                    value = height, // Текущее значение
                    onValueChange = { newValue ->
                        height = newValue // Обновляем состояние
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "см",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Возраст
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Возраст",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(65.dp))

                CustomInputField(
                    value = age, // Текущее значение
                    onValueChange = { newValue ->
                        age = newValue // Обновляем состояние
                    }
                )
            }

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Пол",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )
                Spacer(Modifier.width(65.dp))
                GenderSelection()
            }


            Spacer(modifier = Modifier.height(55.dp))


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Уровень активности",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    ActivitySelection()
                }
            }

            Spacer(modifier = Modifier.height(100.dp))


            Button(
                onClick = {
                    if (weight.isNotEmpty() && height.isNotEmpty() && age.isNotEmpty()) {
                        onNavigateToHome()
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(130.dp)
                    .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Далее",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (weight.isNotEmpty() && height.isNotEmpty() && age.isNotEmpty()) { Color.Black }
                    else { Color.Gray }
                )
            }
        }
    }
}


@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.width(150.dp).height(70.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE4E4E4),
            contentColor = Color.Black
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(70.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 24.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        "Введите...",
                        color = Color.LightGray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}


@Composable
fun GenderSelection() {
    val customShape1 = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 16.dp
    )

    val customShape2 = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 0.dp
    )

    Card(
        Modifier.width(75.dp).height(70.dp),
        shape = customShape1,
        colors = CardColors(
            containerColor = if ( gender == 0 ) { Color.Black } else { Color(0xffE4E4E4)},
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color(0xffE4E4E4))

    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .clickable { gender = 0 },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "М",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if ( gender == 0 ) { Color.White } else { Color.Black }
            )
        }
    }

    Card(
        Modifier.width(75.dp).height(70.dp),
        shape = customShape2,
        colors = CardColors(
            containerColor = if ( gender == 1 ) { Color.Black} else { Color(0xffE4E4E4) },
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color(0xffE4E4E4))

        ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .clickable { gender = 1 },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Ж",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if ( gender == 1 ) { Color.White } else { Color.Black }
            )
        }
    }
}


@Composable
fun ActivitySelection() {
    val customShape1 = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 16.dp
    )
    val customShape2 = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 0.dp
    )
    val customShape3 = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp
    )

    // Card1
    Card(
        Modifier.width(112.dp).height(54.dp),
        shape = customShape1,
        colors = CardColors(
            containerColor = if ( activelvl == 0 ) { Color.Black } else { Color(0xffE4E4E4)},
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color.Black),

        ) {
        Box (
            modifier = Modifier.fillMaxSize().clickable { activelvl = 0 },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "низкий",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = if ( activelvl == 0 ) { Color.White } else { Color.Black }
            )
        }
    }

    // Card2
    Card(
        Modifier.width(112.dp).height(54.dp),
        colors = CardColors(
            containerColor = if ( activelvl == 1 ) { Color.Black } else { Color(0xffE4E4E4)},
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color.Black),
        shape = customShape3
        ) {
        Box (
            modifier = Modifier.fillMaxSize().clickable { activelvl = 1 },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "умеренный",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if ( activelvl == 1 ) { Color.White } else { Color.Black }
            )
        }
    }

    // Card3
    Card(
        Modifier.width(112.dp).height(54.dp),
        shape = customShape2,
        colors = CardColors(
            containerColor = if ( activelvl == 2 ) { Color.Black } else { Color(0xffE4E4E4)},
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color.Black),

        ) {
        Box (
            modifier = Modifier.fillMaxSize().clickable { activelvl = 2 },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "высокий",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if ( activelvl == 2 ) { Color.White } else { Color.Black }
            )
        }
    }
}


@Composable
@Preview
fun PrevInputScreen() {
    val navController = rememberNavController()

    AppThemeWrapper {
        InputScreen { {} }
    }
}