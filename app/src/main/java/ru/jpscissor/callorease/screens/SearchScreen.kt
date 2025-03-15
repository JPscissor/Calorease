package ru.jpscissor.callorease.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper

@Composable
fun SearchScreen(onBack: () -> Unit) {

}

@Preview
@Composable
fun PrevSearchScreen () {
    AppThemeWrapper {
        SearchScreen({})
    }
}