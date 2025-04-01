package ru.jpscissor.callorease.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import ru.jpscissor.callorease.R


enum class AppTheme {
    Light, Dark, Monochrome
}


private val MonochromeColorScheme = lightColorScheme(
    background = Color(0xffF4F4F4),
    onBackground = Color(0xffFFFFFF),

    surface = Color(0xffD2D2D2),
    onSurface = Color(0xFF1C1C1C),

    surfaceVariant = Color(0xffD2D2D2),
    onSurfaceVariant = Color(0xFF1C1C1C),

    inverseSurface = Color(0xffD2D2D2),
    inverseOnSurface = Color(0xFF1C1C1C),

    secondary = Color(0xffD2D2D2),
    onSecondary = Color(0xFF1C1C1C),

    tertiary = Color(0xFF1C1C1C),
    onTertiary = Color.White,
    onTertiaryContainer = Color.LightGray
)

private val DarkColorScheme = darkColorScheme(
    background = Color(0xff292929),
    onBackground = Color(0xff242424),

    surface = Color(0xff444A3B),
    onSurface = Color(0xffBDF168),

    surfaceVariant = Color(0xff444A3B),
    onSurfaceVariant = Color(0xffBDF168),

    inverseSurface = Color(0xff444A3B),
    inverseOnSurface = Color(0xffBDF168),

    secondary = Color(0xff444A3B),
    onSecondary = Color(0xffBDF168),

    tertiary = Color(0xffBDF168),
    onTertiary = Color.Black,
    onTertiaryContainer = Color(0x61BDF168)
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xffF4F4F4),
    onBackground = Color(0xffFFFFFF),

    surface = Color(0xffF1F9E6),
    onSurface = Color(0xffBDF168),

    surfaceVariant = Color(0xffE8E8FF),
    onSurfaceVariant = Color(0xff4F52FF),

    inverseSurface = Color(0xffFFD1D1),
    inverseOnSurface = Color(0xffFF4B4B),

    secondary = Color(0xffDCF2FF),
    onSecondary = Color(0xff6FC8FF),

    tertiary = Color(0xFF1C1C1C),
    onTertiary = Color.White,
    onTertiaryContainer = Color.LightGray
)


@Composable
fun inPreview(): Boolean = LocalInspectionMode.current

@Composable
fun CallorEaseTheme(
    appTheme: AppTheme = AppTheme.Monochrome,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.Light -> LightColorScheme
        AppTheme.Dark -> DarkColorScheme
        AppTheme.Monochrome -> MonochromeColorScheme
    }

    val typography = if (inPreview()) PreviewTypography else Typography

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}


class ThemeManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
    private val SELECTED_THEME_KEY = "selected_theme"

    var selectedTheme: AppTheme
        get() = when (sharedPreferences.getString(SELECTED_THEME_KEY, "Light")) {
            "Dark" -> AppTheme.Dark
            "Monochrome" -> AppTheme.Monochrome
            else -> AppTheme.Light
        }
        set(value) {
            sharedPreferences.edit().putString(SELECTED_THEME_KEY, value.name).apply()
            _currentTheme.value = value
        }

    val _currentTheme: MutableState<AppTheme> = mutableStateOf(selectedTheme)
    val currentTheme: AppTheme
        get() = _currentTheme.value

    fun toggleTheme() {
        selectedTheme = when (selectedTheme) {
            AppTheme.Light -> AppTheme.Dark
            AppTheme.Dark -> AppTheme.Monochrome
            AppTheme.Monochrome -> AppTheme.Light
        }
    }

    fun getCurrentThemeName(): String {
        return when (selectedTheme) {
            AppTheme.Light -> "Светлая тема"
            AppTheme.Dark -> "Тёмная тема"
            AppTheme.Monochrome -> "Монохромная тема"
        }
    }
}


val LocalThemeManager = staticCompositionLocalOf<ThemeManager> {
    error("No ThemeManager provided")
}

@Composable
fun ThemeProvider(
    themeManager: ThemeManager,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalThemeManager provides themeManager) {
        content()
    }
}


@Composable
fun AppThemeWrapper(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val themeManager = remember { ThemeManager(context) }
    val currentTheme by remember { themeManager._currentTheme }

    CallorEaseTheme(appTheme = currentTheme) {
        ThemeProvider(themeManager) {
            content()
        }
    }
}

@Composable
fun currentTheme(): Int {
    if ( Color(0xFF1C1C1C) == MaterialTheme.colorScheme.onSecondary ) { return 0 }
    else if (Color(0xffBDF168) == MaterialTheme.colorScheme.onSecondary) { return 1 }
    else { return 2 }
}