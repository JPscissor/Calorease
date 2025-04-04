package ru.jpscissor.callorease.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.data.InputViewModel
import ru.jpscissor.callorease.navigation.NavRoute
import ru.jpscissor.callorease.ui.theme.AppTheme
import ru.jpscissor.callorease.ui.theme.CallorEaseTheme
import ru.jpscissor.callorease.ui.theme.LocalThemeManager
import ru.jpscissor.callorease.ui.theme.currentTheme


@Composable
fun SplashScreen(onNavigateToOnboard: () -> Unit, onNavigateToHome: () -> Unit, viewModel: InputViewModel) {
    val profile by viewModel.profile.collectAsState()

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        if ( profile.height != 0 ) onNavigateToHome() else onNavigateToOnboard()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter =   if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { painterResource(R.drawable.app_icon_black) }
                            else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary ) { painterResource(R.drawable.app_icon_green) }
                            else if (currentTheme() == 3) { painterResource(R.drawable.kuromi_logo) }
                            else { painterResource(R.drawable.app_icon_green_white) },
                contentDescription = "",
                modifier = Modifier.size(165.dp)
            )
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PrevSplashScreen() {
//    CallorEaseTheme {
//        SplashScreen({},{})
//    }
//}