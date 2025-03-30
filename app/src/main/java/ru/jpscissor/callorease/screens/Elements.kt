package ru.jpscissor.callorease.screens

import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.ui.theme.currentTheme

@Composable
fun ApperPanel(txt: String, nav: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = if ( currentTheme() != 1 ) painterResource(R.drawable.arrow) else painterResource(
                R.drawable.arrow_green
            ),
            contentDescription = "",
            modifier = Modifier.size(30.dp).clickable { nav() }
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = txt,
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiary
        )

        Spacer(Modifier.weight(1f))
    }
}


@Composable
fun systemPaddingWithoutBottom(): PaddingValues {
    val insets = WindowInsets.systemBars.asPaddingValues()
    val hasNavigationBar = WindowInsets.navigationBars.getBottom(LocalDensity.current) > 0

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