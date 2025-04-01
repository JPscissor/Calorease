package ru.jpscissor.callorease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.data.loadProfileFromJson
import ru.jpscissor.callorease.data.saveProfileToJson
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper

@Composable
fun ParamsScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var pass = {}


    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(systemPaddingWithoutBottom())
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(end = 24.dp, start = 24.dp, top = 16.dp, bottom = 16.dp)
        ) {
            ApperPanel("Параметры", if (
                (GlobalParams.weight != 0) && (GlobalParams.weight in 30..300) &&
                GlobalParams.height != 0 && (GlobalParams.height in 100..250) &&
                GlobalParams.age != 0 && (GlobalParams.age in 14..120)
            ) onBack else pass )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(370.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onBackground )
                ) {
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
                            value = weight,
                            onValueChange = { newValue ->
                                weight = newValue
                                GlobalParams.weight = newValue.toIntOrNull() ?: 0
                            },
                            isValid = isCorrect("weight", weight.toIntOrNull() ?: 0)
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
                                value = height,
                                onValueChange = { newValue ->
                                    height = newValue
                                    GlobalParams.height = newValue.toIntOrNull() ?: 0
                                },
                                isValid = isCorrect("height", height.toIntOrNull() ?: 0)
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
                                value = age,
                                onValueChange = { newValue ->
                                    age = newValue
                                    GlobalParams.age = newValue.toIntOrNull() ?: 0
                                },
                                isValid = isCorrect("age", age.toIntOrNull() ?: 0)
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
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(Modifier.weight(1f))
                            GenderSelection()
                        }

                        Spacer(Modifier.height(24.dp))
                    }
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
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ActivitySelection()
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
                            loadProfileFromJson(context)
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
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
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.surface
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