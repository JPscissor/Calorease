package ru.jpscissor.callorease.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.screens.GlobalProgress.progressCalories
import ru.jpscissor.callorease.screens.GlobalProgress.progressCarbohydrates
import ru.jpscissor.callorease.screens.GlobalProgress.progressFats
import ru.jpscissor.callorease.screens.GlobalProgress.progressProteins
import ru.jpscissor.callorease.screens.GlobalProgress.progressWater
import ru.jpscissor.callorease.screens.GlobalProgress.secret
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper

object GlobalProgress {
    var progressCalories by mutableFloatStateOf(0.0F)
    var progressProteins by mutableFloatStateOf(0.0F)
    var progressCarbohydrates by mutableFloatStateOf(0.0F)
    var progressFats by mutableFloatStateOf(0.0F)
    var progressWater by mutableFloatStateOf(0.0F)

    var secret by mutableIntStateOf(0)
}

@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit, onNavigateToAdding: () -> Unit) {

    if (secret == 1500) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(R.drawable.secret), contentDescription = "")
            Text(
                text = "УЧИ УРОКИ!",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 64.sp
            )
        }
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier

                    .padding(horizontal = 20.dp, vertical = 32.dp)
            ) {

                //Upper Panel
                UpperPanel(onNavigateToProfile)

                Spacer(Modifier.height(35.dp))

                //Midle Tiles
                Tiles()

                Spacer(Modifier.height(30.dp))

                //Notes
                Notes()

            }

            Spacer(Modifier.weight(1f))

            BottomPanel(onNavigateToAdding)
        }
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
            painter =   if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { painterResource(R.drawable.app_icon_black) }
                        else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary) { painterResource(R.drawable.app_icon_green) }
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
            painter =   if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { painterResource(R.drawable.ppl) }
                        else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary) { painterResource(R.drawable.ppl_green) }
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
fun Tiles() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(345.dp)
    ) {
        //Calories
        Calories()

        Spacer(Modifier.width(12.dp))

        //Nutrition List
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Proteins() //!
            Spacer(Modifier.height(12.dp))
            Carbohydrates() //!
            Spacer(Modifier.height(12.dp))
            Fats() //1

        }

    }

    Spacer(Modifier.height(12.dp))
    //Water
    WaterCounter()

}


@Composable
fun Calories() {

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
                Counter("Калорий", "1950")

                Spacer(Modifier.height(35.dp))

                Text(
                    "осталось",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }
    }

}


@Composable
fun Proteins() {

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
                SmallCounter("Белки", "150г")

                Spacer(Modifier.height(15.dp))

                Text(
                    "осталось",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }

}


@Composable
fun Carbohydrates() {

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
                SmallCounter("Углев.", "45г")

                Spacer(Modifier.height(15.dp))

                Text(
                    "осталось",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }

}


@Composable
fun Fats() {

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
                SmallCounter("Жиры", "90г")

                Spacer(Modifier.height(15.dp))

                Text(
                    "осталось",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
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
fun WaterCounter() {

    Card(
        modifier = Modifier
            .height(107.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 10.dp),
        ) {

            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "осталось",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.width(5.dp))

                Text(
                    text = "300мл",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Вода",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier =  Modifier.padding(horizontal = 25.dp)
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
fun Notes() {
    Card(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter =   if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { painterResource(R.drawable.pencil) }
                            else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary) { painterResource(R.drawable.pencil_green) }
                            else { painterResource(R.drawable.pencil) },
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Текст заметки...",
                color = if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { Color(0xffD2D2D2) }
                        else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary) { Color(0xff444A3B) }
                        else { Color(0xffD2D2D2) },
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
                )
        }
    }
}



@Composable
fun BottomPanel(onButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(MaterialTheme.colorScheme.onBackground),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onButtonClick,
            modifier = Modifier.size(55.dp)
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

            // Рисуем фон
            drawRect(
                color = backgroundColor,
                topLeft = Offset(0f, 0f),
                size = Size(canvasWidth, canvasHeight)
            )

            // Рисуем прогресс только если он > 0
            if (progress > 0f) {
                drawLine(
                    color = color,
                    start = Offset(0f, canvasHeight / 2),
                    end = Offset(canvasWidth * progress, canvasHeight / 2),
                    strokeWidth = canvasHeight,
                    cap = StrokeCap.Butt // Используем Butt вместо Square
                )
            }
        }
    }
}


@Composable
@Preview
fun PrevHomeScreen() {
    AppThemeWrapper {
        HomeScreen({},{})
    }
}