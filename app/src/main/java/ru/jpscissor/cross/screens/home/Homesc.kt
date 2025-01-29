package ru.jpscissor.cross.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.cross.models.HomeViewModel
import ru.jpscissor.cross.models.UserViewModel
import ru.jpscissor.cross.navigation.NavRoute
import ru.jpscissor.cross.ui.theme.CrossTheme
import kotlin.math.roundToInt

object GlobalData {
    var proteinsNorm = 0.0
    var carbsNorm = 0.0
    var fatsNorm = 0.0
    var calsNorm = 0.0
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomescScreen(
    navController: NavHostController,
    viewModel: UserViewModel = viewModel()
    ) {

    val user by viewModel.user.observeAsState(initial = null)

//    CalcStats()

    var proteins = 0
    var carbs = 0
    var fats = 0
    var cals = 0

    val progressState = remember { mutableDoubleStateOf(0.0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1919),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(vertical = 45.dp),
                containerColor = Color(0xFF79FF57),
                onClick = {
                    TODO("ПРИЕМ ПИЩИ+")
                    TODO("ПРОЦЕНТ ЗАПОЛНЕНИЯ")

                }
            ) {
                Text("+", color = Color.DarkGray, fontSize = 32.sp)
            }

        }
    ) {

        Button(
            onClick = { navController.navigate(NavRoute.Settingsc.route) },
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF79FF57),
                contentColor = Color.DarkGray,
                disabledContainerColor = Color(0xFF79FF57),
                disabledContentColor = Color(0xFF79FF57)
            )
        ) {
            Text(":)", color = Color.DarkGray)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedCircularProgressBar(progressState.doubleValue)

            
            Text(
                text = "Калории: " + CalcCalories(state.value.proteins, state.value.carbs, state.value.fats) + " / " + state.value.calNorm,
                modifier = Modifier.padding(vertical = 25.dp),
                fontSize = 30.sp,
                color = Color.Gray
            )

            Column(
                modifier = Modifier.fillMaxWidth(0.8f).padding(vertical = 40.dp)
            ){

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Белки",
                        modifier = Modifier.padding(),
                        color = Color(0xFFBFBFBF)
                    )

                    Text(
                        text = "${state.value.proteins}/${state.value.proteinsNorm} ",
                        color = Color(0xFFBFBFBF)
                    )
                }

                LinearProgressIndicator(
                    progress = (state.value.proteins.toFloat() / state.value.proteinsNorm.toFloat()),
                    modifier = Modifier.fillMaxWidth().height(10.dp),
                    color = Color(0xFF79FF57),
                    trackColor = Color(0xFFBFBFBF)

                )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Углеводы",
                        modifier = Modifier.padding(),
                        color = Color(0xFFBFBFBF)
                    )

                    Text(
                        text = "${state.value.carbs}/${state.value.carbsNorm} ",
                        color = Color(0xFFBFBFBF)
                    )
                }

                LinearProgressIndicator(
                    progress = (state.value.carbs.toFloat() / state.value.carbsNorm.toFloat()),
                    modifier = Modifier.fillMaxWidth().height(10.dp),
                    color = Color(0xFF79FF57),
                    trackColor = Color(0xFFBFBFBF)
                    )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Жиры",
                        modifier = Modifier.padding(),
                        color = Color(0xFFBFBFBF)
                    )

                    Text(
                        text = "${state.value.fats}/${state.value.fatsNorm} ",
                        color = Color(0xFFBFBFBF)
                    )
                }

                LinearProgressIndicator(
                    progress = (state.value.fats.toFloat() / state.value.fatsNorm.toFloat()),
                    modifier = Modifier.fillMaxWidth().height(10.dp),
                    color = Color(0xFF79FF57),
                    trackColor = Color(0xFFBFBFBF)
                    )
                }

            }

        }
    }
}



@Composable
fun AnimatedCircularProgressBar(targetProgress: Double) {
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(targetProgress) {
        animatedProgress.animateTo(
            targetValue = targetProgress.toFloat(),
            animationSpec = tween(durationMillis = 1000)
        )
    }

    CircularProgressBar(progress = animatedProgress.value, modifier = Modifier.size(200.dp))
}


@Composable
fun CircularProgressBar(progress: Float, modifier: Modifier = Modifier) {
    val strokeWidth = 15.dp
    val backgroundColor = Color.LightGray
    val progressColor = Color(0xFF79FF57)

    Canvas(modifier = modifier) {
        val size = size.maxDimension
        val radius = size / 2

        drawCircle(
            color = backgroundColor,
            radius = radius,
            style = Stroke(width = strokeWidth.toPx())
        )


        drawArc(
            color = progressColor,
            startAngle = -90f,
            sweepAngle = 360f * progress,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}


fun CalcCalories(proteins: Int, carbs: Int, fats: Int): Int {
    return (proteins * 4) + (carbs * 4) + (fats * 9)
}

fun CalcStats(we: Int, he: Int, ag: Int, ge: Int, al: Int) {
    if (ge == 0) {
        if (al == 1){
            GlobalData.calsNorm = (10 * we + 6.25 * he - 5 * ag + 5) * 1.2
            GlobalData.proteinsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.2) / 0.3
            GlobalData.carbsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.2) / 0.3
            GlobalData.fatsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.2) / 0.4
        }
        if (al == 2){
            GlobalData.calsNorm = (10 * we + 6.25 * he - 5 * ag + 5) * 1.55
            GlobalData.proteinsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.55) / 0.3
            GlobalData.carbsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.55) / 0.3
            GlobalData.fatsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.55) / 0.4
        }
        if (al == 3){
            GlobalData.calsNorm = (10 * we + 6.25 * he - 5 * ag + 5) * 1.725
            GlobalData.proteinsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.725) / 0.3
            GlobalData.carbsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.725) / 0.3
            GlobalData.fatsNorm = ((10 * we + 6.25 * he - 5 * ag + 5) * 1.725) / 0.4
        }
    }
    else {
        if (al == 1){
            GlobalData.calsNorm = (10 * we + 6.25 * he - 5 * ag - 161) * 1.2
            GlobalData.proteinsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.2) / 0.3
            GlobalData.carbsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.2) / 0.3
            GlobalData.fatsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.2) / 0.4
        }
        if (al == 2){
            GlobalData.calsNorm = (10 * we + 6.25 * he - 5 * ag - 161) * 1.55
            GlobalData.proteinsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.55) / 0.3
            GlobalData.carbsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.55) / 0.3
            GlobalData.fatsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.55) / 0.4
        }
        if (al == 3){
            GlobalData.calsNorm = (10 * we + 6.25 * he - 5 * ag - 161) * 1.725
            GlobalData.proteinsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.725) / 0.3
            GlobalData.carbsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.725) / 0.3
            GlobalData.fatsNorm = ((10 * we + 6.25 * he - 5 * ag - 161) * 1.725) / 0.4
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevHomescScreen() {
    CrossTheme {
        HomescScreen(navController = rememberNavController())
    }
}