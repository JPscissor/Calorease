package ru.jpscissor.callorease.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.data.ConsumedProduct
import ru.jpscissor.callorease.data.InputViewModel
import ru.jpscissor.callorease.data.WaterData
import ru.jpscissor.callorease.data.getConsumedProductsByTimeRange
import ru.jpscissor.callorease.data.loadAndCalculateTotals
import ru.jpscissor.callorease.data.saveWaterToFile
import ru.jpscissor.callorease.screens.GlobalProgress.globCalories
import ru.jpscissor.callorease.screens.GlobalProgress.globWater
import ru.jpscissor.callorease.screens.GlobalProgress.progressCalories
import ru.jpscissor.callorease.screens.GlobalProgress.progressCarbohydrates
import ru.jpscissor.callorease.screens.GlobalProgress.progressFats
import ru.jpscissor.callorease.screens.GlobalProgress.progressProteins
import ru.jpscissor.callorease.screens.GlobalProgress.progressWater
import ru.jpscissor.callorease.screens.GlobalProgress.secret
import ru.jpscissor.callorease.screens.GlobalProgress.todayWater
import ru.jpscissor.callorease.ui.theme.currentTheme
import java.time.LocalDate


object GlobalProgress {
    var progressCalories by mutableFloatStateOf(0.0F)
    var progressProteins by mutableFloatStateOf(0.0F)
    var progressCarbohydrates by mutableFloatStateOf(0.0F)
    var progressFats by mutableFloatStateOf(0.0F)
    var progressWater by mutableFloatStateOf(0.0F)

    var secret by mutableIntStateOf(0)

    var isDialogVisible by mutableStateOf(false)

    var todayWater by mutableFloatStateOf(0.0F)

    var globCalories by mutableIntStateOf(0)

    var globWater by mutableIntStateOf(0)
}


fun setProteins(calories: Int): Int {
    return ((calories * 0.25) / 4).toInt()
}

fun setCarbohydrates(calories: Int): Int {
    return ((calories * 0.45) / 4).toInt()
}

fun setFats(calories: Int): Int {
    return ((calories * 0.3) / 9).toInt()
}

fun setCalories(weight: Int, height: Int, age: Int, gender: Int, alvl: Int): Int {

    val mod = if (alvl == 0 ) 1.25
    else if ( alvl == 1 ) 1.47
    else 1.6

    val cals = if (gender == 0) ( ((10 * weight) + (6.25 * height) - (5 * age) + 5 ) ) * mod
    else ( (10 * weight) + (6.25 * height) - (5 * age) - 161 ) * mod

    return cals.toInt()
}

fun setWater(weight: Int): Int {
    return weight * 30
}

@Composable
fun HomeScreen(
    onNavigateToMenu: () -> Unit,
    onNavigateToSearch: () -> Unit,
    context: Context
) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(systemPaddingWithoutBottom())
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                //Upper Panel
                UpperPanel(onNavigateToMenu)

                Spacer(Modifier.height(16.dp))

                //Middle Tiles
                Tiles(viewModel(), context = context)

                Spacer(Modifier.height(16.dp))

                //Notes
                DayPeriod()

            }

            Spacer(Modifier.weight(1f))

            BottomPanel(onNavigateToSearch)
        }

    if (GlobalProgress.isDialogVisible) {
        WaterDialog(
            onDismiss = { GlobalProgress.isDialogVisible = false },
            onConfirm = { waterAmount ->
                val today = LocalDate.now().toString()


                GlobalProgress.todayWater += waterAmount

                saveWaterToFile(
                    context,
                    "water.json",
                    WaterData(consumedDate = today, waterAmount = todayWater)
                )
            }
        )
    }
}


@Composable
fun UpperPanel(click: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter =   if ( currentTheme() == 0 ) { painterResource(R.drawable.app_icon_black) }
                        else if ( currentTheme() == 1 ) { painterResource(R.drawable.app_icon_green) }
                        else { painterResource(R.drawable.app_icon_black) },
            modifier = Modifier.width(65.dp).clickable { secret++ },
            contentDescription = ""
        )
        Spacer(Modifier.width(6.dp))

        Text(
            "Calorease",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiary
        )

        Spacer(Modifier.weight(1f))

        Image(
            painter =   if ( currentTheme() == 0 ) { painterResource(R.drawable.ppl) }
                        else if ( currentTheme() == 1 ) { painterResource(R.drawable.ppl_green) }
                        else { painterResource(R.drawable.ppl) },
            contentDescription = "",
            modifier = Modifier
                .width(28.dp)
                .align(Alignment.Bottom)
                .clickable { click() }
        )

        Spacer(modifier = Modifier.width(8.dp))

    }
}


@Composable
fun Tiles(viewModel: InputViewModel, context: Context) {
    val profile by viewModel.profile.collectAsState()

    globCalories = setCalories(
        GlobalParams.weight,
        GlobalParams.height,
        GlobalParams.age,
        GlobalParams.gender,
        GlobalParams.activelvl
    )

    globWater = setWater(GlobalParams.weight)

    val totals = remember {
        loadAndCalculateTotals(context, "consumed.json")
    }
    val consumedCalories = totals["calories"] ?: 0.0
    val consumedProteins = totals["proteins"] ?: 0.0
    val consumedCarbs= totals["carbohydrates"] ?: 0.0
    val consumedFats = totals["fats"] ?: 0.0


////    if (calories > 0) {
////        GlobalProgress.progressCalories = ((consumedCalories / calories) * 100).toFloat().coerceIn(0.0F, 100.0F)
////    }
//
//    // Обновление прогресса белков
//    val proteinsNorm = setProteins(calories)
//    if (proteinsNorm > 0) {
//        GlobalProgress.progressProteins = ((consumedProteins / proteinsNorm) * 100).toFloat().coerceIn(0.0F, 100.0F)
//    }
//
//    // Обновление прогресса углеводов
//    val carbsNorm = setCarbohydrates(calories)
//    if (carbsNorm > 0) {
//        GlobalProgress.progressCarbohydrates = ((consumedCarbs / carbsNorm) * 100).toFloat().coerceIn(0.0F, 100.0F)
//    }
//
//    // Обновление прогресса жиров
//    val fatsNorm = setFats(calories)
//    if (fatsNorm > 0) {
//        GlobalProgress.progressFats = ((consumedFats / fatsNorm) * 100).toFloat().coerceIn(0.0F, 100.0F)
//    }


    if ((todayWater / (globWater * 0.01) / 100).toFloat() >= 1.0) {GlobalProgress.progressWater = 1.0F }
    else {progressWater = (todayWater / (globWater * 0.01) / 100).toFloat()}

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(345.dp)
    ) {
        //Calories
        val caloriesToView = globCalories-consumedCalories
        Calories(caloriesToView)
        GlobalProgress.progressCalories = ((consumedCalories / globCalories.toFloat())).toFloat().coerceIn(0.0F, 1.0F)

        Spacer(Modifier.width(12.dp))

        //Nutrition List
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            val proteinsToView = setProteins(globCalories)-consumedProteins
            Proteins(proteinsToView) //!
            GlobalProgress.progressProteins = ((consumedProteins / setProteins(globCalories).toFloat())).toFloat().coerceIn(0.0F, 1.0F)

            Spacer(Modifier.height(12.dp))

            Carbohydrates(setCarbohydrates(globCalories)-consumedCarbs) //!
            GlobalProgress.progressCarbohydrates = ((consumedCarbs / setCarbohydrates(globCalories).toFloat())).toFloat().coerceIn(0.0F, 1.0F)

            Spacer(Modifier.height(12.dp))
            Fats(setFats(globCalories)-consumedFats) //!
            GlobalProgress.progressFats = ((consumedFats / setFats(globCalories).toFloat())).toFloat().coerceIn(0.0F, 1.0F)

        }

    }

    Spacer(Modifier.height(12.dp))
    //Water
    WaterCounter(globWater.toString())

}


@Composable
fun Calories(calories: Double) {
    var cals = calories
    if (calories < 0) { cals = 0.0 }


    Card(
        modifier = Modifier
            .width(170.dp)
            .height(345.dp),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 35.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Progress
            CircularProgressIndicatorCustom(
                progress = progressCalories,
                size = 140.dp,
                strokeWidth = 10.dp,
                color = MaterialTheme.colorScheme.onSurface,
                backgroundColor = MaterialTheme.colorScheme.surface
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (cals.toInt() != 0) Counter("Калорий", (cals.toInt()).toString())
                else {
                    Text(
                        text = "Калории",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp
                        )
                }


                Spacer(Modifier.height(35.dp))

                Text(
                    text = if (cals.toInt() != 0) {"осталось"}
                    else {"Завершено!"},
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }
    }

}


@Composable
fun Proteins(proteins: Double) {
    var prots = proteins
    if (prots < 0) { prots = 0.0 }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.73.dp),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Progress
            CircularProgressIndicatorCustom(
                progress = progressProteins,
                size = 70.dp,
                strokeWidth = 7.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                if (prots.toInt() != 0 ) {SmallCounter("Белки", (prots.toInt()).toString())}
                else {
                    Text(
                    text = "Белки",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                    )
                }


                Spacer(Modifier.height(15.dp))

                Text(
                    text = if (prots.toInt() != 0) {"осталось"}
                    else {"Завершено!"},
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (prots.toInt() != 0) {13.sp}
                    else {10.sp}
                )
            }
        }
    }

}


@Composable
fun Carbohydrates(carbohydrates: Double) {
    var carbs = carbohydrates
    if (carbs < 0) { carbs = 0.0 }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.73.dp),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            CircularProgressIndicatorCustom(
                progress = progressCarbohydrates,
                size = 70.dp,
                strokeWidth = 7.dp,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                backgroundColor = MaterialTheme.colorScheme.inverseSurface
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                if (carbs.toInt() != 0 ) {SmallCounter("Углев.", (carbs.toInt()).toString())}
                else {
                    Text(
                        text = "Углев.",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    text = if (carbs.toInt() != 0) {"осталось"}
                    else {"Завершено!"},
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (carbs.toInt() != 0) {13.sp}
                    else {10.sp}
                )
            }
        }
    }

}


@Composable
fun Fats(fats: Double) {
    var fat = fats
    if (fat < 0) { fat = 0.0 }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.73.dp),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Progress
            CircularProgressIndicatorCustom(
                progress = progressFats,
                size = 70.dp,
                strokeWidth = 7.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                if (fat.toInt() != 0 ) {SmallCounter("Жиры", (fat.toInt()).toString())}
                else {
                    Text(
                        text = "Жиры",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    text = if (fat.toInt() != 0) {"осталось"}
                    else {"Завершено!"},
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (fat.toInt() != 0) {13.sp}
                    else {10.sp}
                )
            }
        }
    }

}


@Composable
fun Counter(element: String, number: String) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp
        )
        Text(
            text = element,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 16.sp
        )
    }

}

@Composable
fun SmallCounter(element: String, number: String) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = element,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}


@Composable
fun WaterCounter(waterlvl: String) {
    val todayWater = GlobalProgress.todayWater
    val remainingWater = waterlvl.toInt() - todayWater.toInt()

    Card(
        modifier = Modifier
            .height(95.dp)
            .clickable { GlobalProgress.isDialogVisible = true }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 4.dp),
        ) {

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = if (currentTheme() == 1) painterResource(R.drawable.triangle_green)
                                else painterResource(R.drawable.triangle), contentDescription = "", Modifier.size(8.dp))

                Spacer(Modifier.weight(1f))

                Text(
                    text = if ((waterlvl.toInt() - todayWater.toInt()) >= 0 ) {
                        "осталось" }
                    else {"завершено!"},
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.width(5.dp))

                Text(
                    text = if (remainingWater >= 0) "${remainingWater}мл" else "",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Вода",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            CustomLinearProgressIndicator(
                progress = progressWater,
                height = 8.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.secondary
            )

        }

    }

}


@Composable
fun DayPeriod() {
    val breakfastProducts = getConsumedProductsByTimeRange(
        context = LocalContext.current,
        filename = "consumed.json",
        startTime = "00:00",
        endTime = "11:00"
    )

    val lunchProducts = getConsumedProductsByTimeRange(
        context = LocalContext.current,
        filename = "consumed.json",
        startTime = "12:00",
        endTime = "16:00"
    )

    val dinnerProducts = getConsumedProductsByTimeRange(
        context = LocalContext.current,
        filename = "consumed.json",
        startTime = "17:00",
        endTime = "23:00"
    )

    val currentPhase = dayPhase()

    val products = when (currentPhase) {
        "Завтрак" -> breakfastProducts
        "Обед" -> lunchProducts
        "Ужин" -> dinnerProducts
        else -> emptyList()
    }

    Card(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Image(painter = if (currentTheme() == 1) painterResource(R.drawable.triangle_green)
//                else painterResource(R.drawable.triangle), contentDescription = "", Modifier.size(8.dp))
//
//                Spacer(Modifier.width(4.dp))

                Text(
                    text = currentPhase,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "(${products.sumOf { it.calories.toInt() }}Ккал)",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(4.dp))


            if (products.isEmpty()) {
                Text(
                    text = "Здесь пока ничего нет :(",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            else {
                LazyColumn(

                ) {
                    items(products) { product ->
                        ProductItem(product)
                    }
                }
            }
        }
    }
}




@Composable
fun ProductItem(product: ConsumedProduct) {
    val productName = if (product.productName.length > 20) {
        product.productName.substring(0, 20) + "..."
    } else {
        product.productName
    }

    Row(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Text(
            text = productName,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = product.grams.toString(),
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}




@Composable
fun BottomPanel(onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onButtonClick,
            modifier = Modifier.size(70.dp).padding(top = 8.dp)
        ) {
            Image(
                painter =   if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { painterResource(R.drawable.plus_button) }
                            else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary) { painterResource(R.drawable.plus_button_green) }
                            else { painterResource(R.drawable.plus_button) },
                contentDescription = ""
            )
        }
    }
}


@Composable
fun CircularProgressIndicatorCustom(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 140.dp,
    strokeWidth: Dp = 10.dp,
    color: Color = MaterialTheme.colorScheme.onSecondary,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Box(
        modifier = modifier.size(size)
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val strokeWidthPx = strokeWidth.toPx()
            val sizePx = size.toPx()
            val circleSizePx = sizePx - strokeWidthPx * 2


            drawCircle(
                color = backgroundColor,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
                center = Offset(sizePx / 2, sizePx / 2),
                radius = circleSizePx / 2
            )


            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Square),
                topLeft = Offset(strokeWidthPx, strokeWidthPx),
                size = Size(circleSizePx, circleSizePx)
            )
        }
    }
}


@Composable
fun CustomLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 8.dp,
    color: Color = Color.Black,
    backgroundColor: Color = Color.LightGray
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawRect(
                color = backgroundColor,
                topLeft = Offset(0f, 0f),
                size = Size(canvasWidth, canvasHeight)
            )

            if (progress > 0f) {
                drawLine(
                    color = color,
                    start = Offset(0f, canvasHeight / 2),
                    end = Offset(canvasWidth * progress, canvasHeight / 2),
                    strokeWidth = canvasHeight,
                    cap = StrokeCap.Butt
                )
            }
        }
    }
}


@Composable
fun WaterDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var waterInput by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val isValid = waterInput.toIntOrNull() != null && waterInput.toIntOrNull()!! > 0

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Добавление воды",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = waterInput,
                    shape = RoundedCornerShape(15.dp),
                    onValueChange = { newValue ->
                        waterInput = newValue.filter { it.isDigit() }
                    },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.Center,
                        color = if (!isValid) Color.Red else MaterialTheme.colorScheme.tertiary,
                        fontSize = 24.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        cursorColor = MaterialTheme.colorScheme.tertiary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        if (!isFocused && waterInput.isEmpty()) {
                            Text(
                                "Введите...",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.secondary)
                        ) {
                        Text("Отмена", color = MaterialTheme.colorScheme.tertiary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val waterAmount = waterInput.toIntOrNull() ?: 0
                            if (waterAmount > 0) {
                                onConfirm(waterAmount)
                                onDismiss()
                            }
                        },
                        enabled = isValid,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.secondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Добавить", color = MaterialTheme.colorScheme.tertiary)
                    }
                }
            }
        }
    }
}



//@Composable
//@Preview
//fun PrevHomeScreen() {
//    AppThemeWrapper {
//        HomeScreen({}, {})
//    }
//}