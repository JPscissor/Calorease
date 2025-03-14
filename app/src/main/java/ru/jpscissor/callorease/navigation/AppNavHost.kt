package ru.jpscissor.callorease.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.screens.HomeScreen
import ru.jpscissor.callorease.screens.InputScreen
import ru.jpscissor.callorease.screens.MenuScreen
import ru.jpscissor.callorease.screens.OnboardScreen
import ru.jpscissor.callorease.screens.ParamsScreen
import ru.jpscissor.callorease.screens.ProfileScreen
import ru.jpscissor.callorease.screens.SplashScreen
import ru.jpscissor.callorease.screens.ThemeSelectionScreen
import ru.jpscissor.callorease.screens.ThemeSetter
import ru.jpscissor.callorease.screens.ThemeTestScreen


sealed class NavRoute (val route: String) {
    object Onboard: NavRoute("onboard_screen")
    object Input: NavRoute("input_screen")
    object Home: NavRoute("home_screen")
    object Splash: NavRoute("splash_screen")
    object Profile: NavRoute("profile_screen")
    object Themetest: NavRoute("themetest_screen")
    object ThemeSelection: NavRoute("themeselection_screen")
    object Menu: NavRoute("menu_screen")
    object Adding: NavRoute("adding_screen")
    object ThemeSetter: NavRoute("themesetter_screen")
    object Params: NavRoute("params_screen")
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
        SplashScreen(
            onNavigateToOnboard = {
                navController.navigate(NavRoute.Onboard.route) {
                    popUpTo(NavRoute.Splash.route) { inclusive = true }
                }
            },
            onNavigateToHome = {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Splash.route) { inclusive = true }
                }
            },
            viewModel()
        )
    }

    composable(NavRoute.Onboard.route) {
        OnboardScreen(
            onNavigateToSelect = {
                navController.navigate(NavRoute.ThemeSelection.route) {
                    popUpTo(NavRoute.Onboard.route) { inclusive = true }
                }
            }
        )
    }

    composable(NavRoute.ThemeSelection.route) {
        ThemeSelectionScreen(
            onBack = {
                navController.navigate(NavRoute.Input.route) {
                    popUpTo(NavRoute.ThemeSelection.route) { inclusive = true }
                }
            }
        )
    }

    composable(NavRoute.Input.route) {
        InputScreen(
            onNavigateToHome = {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Input.route) { inclusive = true }
                }
            }
        )
    }
}


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(NavRoute.Home.route) {
        HomeScreen(
            onNavigateToMenu = { navController.navigate(NavRoute.Menu.route) },
            onNavigateToAdding = { navController.navigate(NavRoute.Adding.route) },
        )
    }

    composable(NavRoute.Menu.route) {
        MenuScreen(
            onNavigateToParams = { navController.navigate(NavRoute.Params.route)},
            onNavigateToThemeSetter = { navController.navigate(NavRoute.ThemeSetter.route)},
            onBack = {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Menu.route) { inclusive = true }
                }
            }
        )

    }


    composable(NavRoute.Params.route) {
        ParamsScreen (
            onBack = {
                navController.navigate(NavRoute.Menu.route) {
                    popUpTo(NavRoute.Params.route) { inclusive = true }
                }
            }
        )

    }


    composable(NavRoute.ThemeSetter.route) {
        ThemeSetter(
            onBack = {
                navController.navigate(NavRoute.Menu.route) {
                    popUpTo(NavRoute.ThemeSetter.route) { inclusive = true }
                }
            }
        )

    }


    composable(NavRoute.Profile.route) {
        ProfileScreen(
            viewModel(),
            onNavigateToThemeTest = {
            navController.navigate(NavRoute.Themetest.route)
            },
            onBack = {
                navController.navigate(NavRoute.Menu.route) {
                    popUpTo(NavRoute.Profile.route) { inclusive = true }
                }
            }
        )
    }


    composable(NavRoute.Themetest.route) {
        ThemeTestScreen(onBack = {navController.navigate(NavRoute.ThemeSelection.route)})
    }
}