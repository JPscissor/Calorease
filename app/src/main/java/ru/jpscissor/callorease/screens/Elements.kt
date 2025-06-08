package ru.jpscissor.callorease.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.ui.theme.currentTheme
import java.time.LocalDateTime
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


@Composable
fun ApperPanel(txt: String, nav: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            if ( currentTheme() == 0 ) painterResource(R.drawable.arrow)
            else if (currentTheme() == 1) painterResource(R.drawable.arrow_green)
            else painterResource(R.drawable.arrow),
            contentDescription = "",
            modifier = Modifier.size(30.dp).clickable { nav() }
        )

        Text(
            text = txt,
            fontSize = if (txt.length > 20) 24.sp else 36.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )


    }
}


@Composable
fun ProductApperPanel(txt: String, nav: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter =
            if ( currentTheme() == 0 ) painterResource(R.drawable.arrow)
            else if (currentTheme() == 1) painterResource(R.drawable.arrow_green)
            else painterResource(R.drawable.arrow),
            contentDescription = "",
            modifier = Modifier.size(30.dp).clickable { nav() }
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = txt,
            fontSize = if (txt.length > 20) 20.sp else 36.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(1f))
    }
}


@Composable
fun systemPaddingWithoutBottom(): PaddingValues {
    val insets = WindowInsets.systemBars.asPaddingValues()
    val hasNavigationBar = WindowInsets.navigationBars.getBottom(LocalDensity.current) > 100

    return if (hasNavigationBar) {
        insets
    } else {
        PaddingValues(
            top = insets.calculateTopPadding(),
            bottom = 0.dp,
            start = insets.calculateStartPadding(LocalLayoutDirection.current),
            end = insets.calculateEndPadding(LocalLayoutDirection.current)
        )
    }
}

@Composable
fun systemPadding(): PaddingValues {
    return WindowInsets.systemBars.asPaddingValues()
}


fun dayPhase(): String {
    val currentHour = LocalDateTime.now().hour

    return when {
        currentHour in 0..11 -> "Завтрак"
        currentHour in 12..16 -> "Обед"
        currentHour in 17..23 -> "Ужин"
        else -> "Неизвестная фаза"
    }
}



@Composable
fun ShowToast(message: String) {
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}