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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.ApperPanel
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.currentTheme

@Composable
fun MenuScreen(onNavigateToParams: () -> Unit, onNavigateToThemeSetter: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 36.dp)
        ) {

            ApperPanel("Профиль", onBack)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().height(100.dp).padding(),
                    onClick = { onNavigateToParams() },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Black)
                ) {
                    Spacer(Modifier.width(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Image(painter = if (currentTheme() != 1) painterResource(R.drawable.params)
                        else painterResource(R.drawable.params_green),
                            contentDescription = "",
                            modifier = Modifier.size(33.dp))

                        Spacer(Modifier.weight(1f))

                        Text(
                            "Параметры",
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.tertiary
                            )
                        Spacer(Modifier.weight(1f))
                    }
                }

                Spacer(Modifier.height(15.dp))
                Button(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    onClick = { onNavigateToThemeSetter() },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Black)
                ) {
                    Spacer(Modifier.width(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Image(painter = if (currentTheme() != 1) painterResource(R.drawable.albm)
                        else painterResource(R.drawable.albm_green),
                            contentDescription = "",
                            modifier = Modifier.size(33.dp))

                        Spacer(Modifier.weight(1f))

                        Text(
                            "Внешний вид",
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(Modifier.weight(1f))
                    }

                }

                Spacer(Modifier.height(15.dp))
                Button(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    onClick = {},
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Black)
                ) {
                    Spacer(Modifier.width(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Image(painter = if (currentTheme() != 1) painterResource(R.drawable.graph)
                        else painterResource(R.drawable.graph_green),
                            contentDescription = "",
                            modifier = Modifier.size(33.dp))

                        Spacer(Modifier.weight(1f))

                        Text(
                            "Статистика",
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(Modifier.weight(1f))
                    }
                }

            }

        }


    }
}


@Composable
@Preview
fun PrevMenuScreen() {
    AppThemeWrapper {
        MenuScreen({},{},{})
    }
}