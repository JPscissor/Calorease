package ru.jpscissor.callorease.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.jpscissor.callorease.screens.AddingScreen
import ru.jpscissor.callorease.screens.HomeScreen
import ru.jpscissor.callorease.screens.InputScreen
import ru.jpscissor.callorease.screens.MenuScreen
import ru.jpscissor.callorease.screens.OnboardScreen
import ru.jpscissor.callorease.screens.ParamsScreen
import ru.jpscissor.callorease.screens.SearchScreen
import ru.jpscissor.callorease.screens.SplashScreen
import ru.jpscissor.callorease.screens.ThemeSelectionScreen
import ru.jpscissor.callorease.screens.ThemeSetter


sealed class NavRoute (val route: String) {
    object Onboard: NavRoute("onboard_screen")
    object Input: NavRoute("input_screen")
    object Home: NavRoute("home_screen")
    object Splash: NavRoute("splash_screen")
    object Profile: NavRoute("profile_screen")
    object Themetest: NavRoute("themetest_screen")
    object ThemeSelection: NavRoute("themeselection_screen")
    object Menu: NavRoute("menu_screen")
    object ThemeSetter: NavRoute("themesetter_screen")
    object Params: NavRoute("params_screen")
    object Search: NavRoute("search_screen")
    object Adding: NavRoute("adding_screen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavRoute.Splash.route) {

        splashGraph(navController)

        mainGraph(navController)

        addingGraph(navController)

    }

}


fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(
        NavRoute.Splash.route
    ) {
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

    composable(
        NavRoute.Onboard.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        OnboardScreen(
            onNavigateToSelect = {
                navController.navigate(NavRoute.Input.route) {
                    popUpTo(NavRoute.Onboard.route) { inclusive = true }
                }
            }
        )
    }

    composable(
        NavRoute.Input.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        InputScreen(
            onNavigateToHome = {
                navController.navigate(NavRoute.ThemeSelection.route) {
                    popUpTo(NavRoute.Input.route) { inclusive = true }
                }
            }
        )
    }

    composable(
        NavRoute.ThemeSelection.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        ThemeSelectionScreen(
            onBack = {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.ThemeSelection.route) { inclusive = true }
                }
            }
        )
    }
}


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(
        NavRoute.Home.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        HomeScreen(
            onNavigateToMenu = { navController.navigate(NavRoute.Menu.route) },
            onNavigateToSearch = { navController.navigate(NavRoute.Search.route) },
            context = LocalContext.current
        )
    }

    composable(
        NavRoute.Menu.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        MenuScreen(
            onNavigateToParams = { navController.navigate(NavRoute.Params.route) },
            onNavigateToThemeSetter = { navController.navigate(NavRoute.ThemeSetter.route) },
            onBack = {
                navController.popBackStack()
            }
        )
    }

    composable(
        NavRoute.Params.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        ParamsScreen(
            onBack = {
                navController.popBackStack()
            }
        )
    }

    composable(
        NavRoute.ThemeSetter.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        ThemeSetter(
            onBack = {
                navController.popBackStack()
            }
        )
    }
}



fun NavGraphBuilder.addingGraph(navController: NavController) {
    composable(
        NavRoute.Search.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        SearchScreen(
            onBack = {
                navController.popBackStack()
            },
            onProductSelect = { navController.navigate(NavRoute.Adding.route) },
            context = LocalContext.current
        )
    }

    composable(
        NavRoute.Adding.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        AddingScreen(
            onBack = {
                navController.popBackStack()
            },
            onComplete = {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Adding.route) { inclusive = true }
                }
            }
        )
    }
}
