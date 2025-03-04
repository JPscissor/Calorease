package ru.jpscissor.callorease.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.screens.HomeScreen
import ru.jpscissor.callorease.screens.InputScreen
import ru.jpscissor.callorease.screens.OnboardScreen
import ru.jpscissor.callorease.screens.ProfileScreen
import ru.jpscissor.callorease.screens.SplashScreen
import ru.jpscissor.callorease.screens.ThemeSelectionScreen
import ru.jpscissor.callorease.screens.ThemeTestScreen
import ru.jpscissor.callorease.ui.theme.ThemeManager


sealed class NavRoute (val route: String) {
    object Onboard: NavRoute("onboard_screen")
    object Input: NavRoute("input_screen")
    object Home: NavRoute("home_screen")
    object Splash: NavRoute("splash_screen")
    object Profile: NavRoute("profile_screen")
    object Themetest: NavRoute("themetest_screen")
    object ThemeSelection: NavRoute("themeselection_screen")
    object Adding: NavRoute("adding_screen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavRoute.Splash.route) {

        splashGraph(navController)

        mainGraph(navController)

    }

}


fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(NavRoute.Splash.route) {
        SplashScreen(onNavigateToOnboard = { navController.navigate(NavRoute.Onboard.route) })
    }

    composable(NavRoute.Onboard.route) {
        OnboardScreen(onNavigateToSelect = { navController.navigate(NavRoute.ThemeSelection.route) })
    }

    composable(NavRoute.ThemeSelection.route) {
        ThemeSelectionScreen(ThemeManager(LocalContext.current), onBack = {navController.navigate(NavRoute.Input.route)})
    }

    composable(NavRoute.Input.route) {
        InputScreen(onNavigateToHome =  {
            navController.navigate(NavRoute.Home.route) {
                popUpTo(NavRoute.Input.route) { inclusive = true }
            } })
    }

}


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(NavRoute.Home.route) {
        HomeScreen(
            onNavigateToProfile = { navController.navigate(NavRoute.Profile.route) },
            onNavigateToAdding = { navController.navigate(NavRoute.Adding.route) },
            onNvigateToThemetest = { navController.navigate(NavRoute.Themetest.route) }
        )
    }

    composable(NavRoute.Profile.route) {
        ProfileScreen(onNavigateToThemeTest = {
            navController.navigate(NavRoute.Themetest.route)
        })
    }
    composable(NavRoute.Themetest.route) {
        ThemeTestScreen(onBack = {navController.navigate(NavRoute.ThemeSelection.route)})
    }
}