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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.currentTheme

@Composable
fun AddingScreen( onBack: () -> Unit, onComplete: () -> Unit) {
    var pweight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(systemPaddingWithoutBottom())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            ApperPanel("Продукт", onBack)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                    text = "Пищевая ценность",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = " (на 100гр.)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Белки",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = "0,0",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Углеводы",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = "0,0",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Жиры",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = "0,0",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    "Вес порции",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Вес",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(Modifier.weight(1f))

                        CustomInputField2(
                            value = pweight,
                            onValueChange = { newValue ->
                                pweight = newValue
                            }
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                Card(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Калорий в порции:",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(Modifier.weight(1f))

                        Text(
                            text = "0",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                Button(
                    onClick = if ( pweight > 0.toString()) {onComplete} else {{}},
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Готово",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }


            }

        }
    }
}



@Composable
fun CustomInputField2(
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.width(150.dp).height(70.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor =  if (currentTheme() == 1) MaterialTheme.colorScheme.background
            else Color(0xFFE4E4E4),
            contentColor =  Color.Black
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)
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
                    color = if (currentTheme() == 1) MaterialTheme.colorScheme.tertiary
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
@Preview
fun PrevAddingScreen() {
    AppThemeWrapper {
        AddingScreen({}, {})
    }
}