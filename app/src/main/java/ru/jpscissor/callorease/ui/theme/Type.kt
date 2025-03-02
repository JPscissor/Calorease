package ru.jpscissor.callorease.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.R

private val Montserrat = FontFamily(
    Font(resId = R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(resId = R.font.montserrat_thin, weight = FontWeight.Thin),
    Font(resId = R.font.montserrat_black, weight = FontWeight.Black),
    Font(resId = R.font.montserrat_light, weight = FontWeight.Light),
    Font(resId = R.font.montserrat_extralight, weight = FontWeight.ExtraLight),
    Font(resId = R.font.montserrat_extrabold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.montserrat_regular, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = TextBlack
    )

)

val PreviewTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
)