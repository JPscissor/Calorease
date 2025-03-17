package ru.jpscissor.callorease.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.data.saveProfileToJson
import ru.jpscissor.callorease.screens.GlobalParams.activelvl
import ru.jpscissor.callorease.screens.GlobalParams.gender
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.currentTheme


object GlobalParams {
    var gender by mutableIntStateOf(0)
    var activelvl by mutableIntStateOf(0)
    var weight by mutableIntStateOf(0)
    var height by mutableIntStateOf(0)
    var age by mutableIntStateOf(0)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "InvalidColorHexValue")
@Composable
fun InputScreen(onNavigateToHome: () -> Unit) {
    val context = LocalContext.current

    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(systemPadding())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
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
                        GlobalParams.weight = newValue.toIntOrNull() ?: 0
                    },
                    isValid = isCorrect("weight", weight.toIntOrNull() ?: 0)
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
                    value = height,
                    onValueChange = { newValue ->
                        height = newValue
                        GlobalParams.height = newValue.toIntOrNull() ?: 0
                    },
                    isValid = isCorrect("height", height.toIntOrNull() ?: 0)
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
                    value = age,
                    onValueChange = { newValue ->
                        age = newValue
                        GlobalParams.age = newValue.toIntOrNull() ?: 0
                    },
                    isValid = isCorrect("age", age.toIntOrNull() ?: 0)
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

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (
                        (GlobalParams.weight != 0) && (GlobalParams.weight in 30..300) &&
                        GlobalParams.height != 0 && (GlobalParams.height in 100..250) &&
                        GlobalParams.age != 0 && (GlobalParams.age in 14..120)
                    ) {
                        saveProfileToJson(context)
                        onNavigateToHome()
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
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
                    color = if (
                        GlobalParams.weight != 0 &&
                        GlobalParams.height != 0 &&
                        GlobalParams.age != 0
                    ) {
                        Color.Black
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}


@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean
) {
    var isFocused by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.width(150.dp).height(70.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor =  if (currentTheme() == 1) MaterialTheme.colorScheme.background
            else if (!isValid) Color(0xffFFE8E8)
            else Color(0xFFE4E4E4),
            contentColor =  Color.Black
        ),
        border = if (!isValid) { BorderStroke(1.5.dp, Color(0xffFF9A9A)) }
        else { BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary) }
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
                    .height(70.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                    color = if (!isValid) Color(0xffFF3939)
                    else if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary
                    else Color.Black,
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
                    if (!isFocused && value.isEmpty()) {
                        Text(
                            "Введите...",
                            color = Color.LightGray,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }

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
            containerColor =
            if ( gender == 0 ) { if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black }
            else { if (currentTheme() == 1) MaterialTheme.colorScheme.background else Color(0xffE4E4E4) },
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color(0xffE4E4E4)
        )

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
                color = if ( gender == 0 ) {
                    if (currentTheme() == 1) MaterialTheme.colorScheme.onBackground
                    else Color.White }
                else { if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black }
            )
        }
    }

    Card(
        Modifier.width(75.dp).height(70.dp),
        shape = customShape2,
        colors = CardColors(
            containerColor =
            if ( gender == 1 ) { if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black }
            else { if (currentTheme() == 1) MaterialTheme.colorScheme.background else Color(0xffE4E4E4) },
            contentColor = Color.Black,
            disabledContainerColor = Color(0xffE4E4E4),
            disabledContentColor = Color(0xffE4E4E4)
        )

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
                color = if ( gender == 1 ) {
                    if (currentTheme() == 1) MaterialTheme.colorScheme.onBackground
                    else Color.White }
                else { if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black }
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

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        // Card1
        Card(
            Modifier.width(112.dp).height(54.dp),
            shape = customShape1,
            colors = CardColors(
                containerColor = if (activelvl == 0) {
                    if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black
                } else {
                    if(currentTheme() == 1) MaterialTheme.colorScheme.onBackground  else Color(0xffE4E4E4)
                },
                contentColor = Color.Black,
                disabledContainerColor = Color(0xffE4E4E4),
                disabledContentColor = Color.Black
            ),

            ) {
            Box(
                modifier = Modifier.fillMaxSize().clickable { activelvl = 0 },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "низкий",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = if (activelvl == 0) {
                        if (currentTheme() == 1) MaterialTheme.colorScheme.onBackground else Color.White
                    } else {
                        if(currentTheme() == 1) MaterialTheme.colorScheme.tertiary  else Color.Black
                    },
                )
            }
        }

        // Card2
        Card(
            Modifier.width(112.dp).height(54.dp),
            colors = CardColors(
                containerColor = if (activelvl == 1) {
                    if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black
                } else {
                    if(currentTheme() == 1) MaterialTheme.colorScheme.onBackground  else Color(0xffE4E4E4)
                },
                contentColor = Color.Black,
                disabledContainerColor = Color(0xffE4E4E4),
                disabledContentColor = Color.Black
            ),
            shape = customShape3
        ) {
            Box(
                modifier = Modifier.fillMaxSize().clickable { activelvl = 1 },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "умеренный",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = if (activelvl == 1) {
                        if (currentTheme() == 1) MaterialTheme.colorScheme.onBackground else Color.White
                    } else {
                        if(currentTheme() == 1) MaterialTheme.colorScheme.tertiary  else Color.Black
                    },
                )
            }
        }

        // Card3
        Card(
            Modifier.width(112.dp).height(54.dp),
            shape = customShape2,
            colors = CardColors(
                containerColor = if (activelvl == 2) {
                    if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary else Color.Black
                } else {
                    if(currentTheme() == 1) MaterialTheme.colorScheme.onBackground  else Color(0xffE4E4E4)
                },
                contentColor = Color.Black,
                disabledContainerColor = Color(0xffE4E4E4),
                disabledContentColor = Color.Black
            ),

            ) {
            Box(
                modifier = Modifier.fillMaxSize().clickable { activelvl = 2 },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "высокий",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = if (activelvl == 2) {
                        if (currentTheme() == 1) MaterialTheme.colorScheme.onBackground else Color.White
                    } else {
                        if(currentTheme() == 1) MaterialTheme.colorScheme.tertiary  else Color.Black
                    },
                )
            }
        }
    }
}


fun isCorrect(paramName: String, value: Int): Boolean {
    return when (paramName) {
        "weight" -> value in 30..300 || value == 0
        "height" -> value in 100..250 || value == 0
        "age" -> value in 14..120 || value == 0
        else -> false
    }
}



@Composable
@Preview
fun PrevInputScreen() {
    AppThemeWrapper {
        InputScreen { {} }
    }
}