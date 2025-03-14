package ru.jpscissor.callorease.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import ru.jpscissor.callorease.ApperPanel
import ru.jpscissor.callorease.data.saveProfileToJson
import ru.jpscissor.callorease.screens.GlobalParams.age
import ru.jpscissor.callorease.screens.GlobalParams.height
import ru.jpscissor.callorease.screens.GlobalParams.weight
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper

@Composable
fun ParamsScreen(onBack: () -> Unit) {
    val context = LocalContext.current


    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 36.dp)
        ) {
            ApperPanel("Параметры", onBack)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onBackground )
                ){
                    Column(
                        modifier = Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 21.dp),
                        verticalArrangement = Arrangement.Center
                    ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Вес",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(Modifier.weight(1f))
                        CustomInputField(
                            value = GlobalParams.weight.toString(),
                            onValueChange = { newValue ->
                                GlobalParams.weight = newValue.toIntOrNull() ?: 0
                            },
                            isValid = isCorrect("weight", weight ?: 0)
                        )
                    }

                        Spacer(Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Рост",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(Modifier.weight(1f))
                            CustomInputField(
                                value = GlobalParams.height.toString(),
                                onValueChange = { newValue ->
                                    GlobalParams.height = newValue.toIntOrNull() ?: 0
                                },
                                isValid = isCorrect("height", height ?: 0)
                            )
                        }

                        Spacer(Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Возраст",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(Modifier.weight(1f))
                            CustomInputField(
                                value = GlobalParams.age.toString(),
                                onValueChange = { newValue ->
                                    GlobalParams.age = newValue.toIntOrNull() ?: 0
                                },
                                isValid = isCorrect("age", age ?: 0)
                            )
                        }

                        Spacer(Modifier.height(24.dp))

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
                            Spacer(Modifier.weight(1f))
                            GenderSelection()
                        }

                        Spacer(Modifier.height(24.dp))

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
                    }
                }

                Spacer(Modifier.height(45.dp))

                Button(
                    onClick = {
                        if (
                            (GlobalParams.weight != 0) && (GlobalParams.weight in 30..300) &&
                            GlobalParams.height != 0 && (GlobalParams.height in 100..250) &&
                            GlobalParams.age != 0 && (GlobalParams.age in 14..120)
                        ) {
                            saveProfileToJson(context)
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Сохранить",
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
}


@Composable
@Preview
fun PrevParamsScreen() {
    AppThemeWrapper {
        ParamsScreen({})
    }
}