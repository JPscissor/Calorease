package ru.jpscissor.callorease.screens

 import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxHeight
 import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
 import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.screens.GlobalIndex.ind
import ru.jpscissor.callorease.ui.theme.AppTheme
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
 import ru.jpscissor.callorease.ui.theme.LocalThemeManager
 import ru.jpscissor.callorease.ui.theme.ThemeManager

@Composable
fun ThemeSetter(onBack: () -> Unit) {
    val themeManager = LocalThemeManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(systemPaddingWithoutBottom())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp,  vertical = 16.dp)
        ) {

            ApperPanel("Изменить тему", onBack)

            Column(
                modifier = Modifier
                    .fillMaxWidth().fillMaxHeight()
                    .padding(vertical = 64.dp),
                verticalArrangement = Arrangement.Center
            ) {

                DefaultCard(painterResource(R.drawable.chbth), painterResource(R.drawable.mnex), 1)

                Spacer(Modifier.height(24.dp))

                DefaultCard(painterResource(R.drawable.dkth), painterResource(R.drawable.dkex), 2)

                Spacer(Modifier.height(24.dp))

                DefaultCard(painterResource(R.drawable.ltth), painterResource(R.drawable.ltex), 3)

                Spacer(Modifier.height(128.dp))

                Button(
                    onClick = {
                        if (ind == 1) { themeManager.selectedTheme = AppTheme.Monochrome }
                        else if (ind == 2) { themeManager.selectedTheme = AppTheme.Dark; }
                        else if (ind == 3) { themeManager.selectedTheme = AppTheme.Light; }
                    },
                    modifier = Modifier.height(50.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text("Сохранить", fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }



        }
    }

}

@Composable
@Preview
fun PrevThemeSetter() {
    AppThemeWrapper {
        val themeManager = ThemeManager(LocalContext.current)
        ThemeSetter({})
    }
}