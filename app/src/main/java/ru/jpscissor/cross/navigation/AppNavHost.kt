package ru.jpscissor.cross.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.cross.screens.home.HomescScreen
import ru.jpscissor.cross.screens.loging.LogscScreen
import ru.jpscissor.cross.screens.SplashscScreen
import ru.jpscissor.cross.screens.settings.SettingscScreen

sealed class NavRoute (val route: String) {
    object Logsc: NavRoute("log_screen")
    object Homesc: NavRoute("home_screen")
    object Splashsc: NavRoute("splash_screen")
    object Settingsc: NavRoute("setting_screen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavRoute.Splashsc.route) {
        composable(NavRoute.Logsc.route){ LogscScreen(navController) }
        composable(NavRoute.Homesc.route){ HomescScreen(navController) }
        composable(NavRoute.Splashsc.route){ SplashscScreen(navController)}
        composable(NavRoute.Settingsc.route){ SettingscScreen(navController) }
    }

}