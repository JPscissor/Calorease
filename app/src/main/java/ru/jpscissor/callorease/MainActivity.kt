package ru.jpscissor.callorease

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.jpscissor.callorease.data.clearOldData
import ru.jpscissor.callorease.data.clearOldWater
import ru.jpscissor.callorease.data.fileExistsInInternalStorage
import ru.jpscissor.callorease.data.initializeConsumedProductsFile
import ru.jpscissor.callorease.data.initializeWaterFile
import ru.jpscissor.callorease.data.loadConsumedProductsFromFile
import ru.jpscissor.callorease.data.loadProfileFromJson
import ru.jpscissor.callorease.data.loadWaterFromFile
import ru.jpscissor.callorease.data.migrateOldData
import ru.jpscissor.callorease.data.migrateOldWaterData
import ru.jpscissor.callorease.navigation.AppNavHost
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.CallorEaseTheme
import ru.jpscissor.callorease.ui.theme.ThemeManager

class MainActivity : ComponentActivity() {

    private lateinit var themeManager: ThemeManager

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Log.d("onCreate", "Loading profile from JSON")
            loadProfileFromJson(this)

            Log.d("onCreate", "Checking if consumed.json exists")
            if (fileExistsInInternalStorage(this, "consumed.json")) {
                Log.d("onCreate", "Migrating old data")
                migrateOldData(this, "consumed.json")
            } else {
                Log.d("onCreate", "Initializing consumed.json")
                initializeConsumedProductsFile(this, "consumed.json")
            }

            Log.d("onCreate", "Clearing old data")
            clearOldData(this, "consumed.json")

            Log.d("onCreate", "Initialization completed")
        } catch (e: Exception) {
            Log.e("onCreate", "Error during initialization", e)
        }

        try {
            if (fileExistsInInternalStorage(this, "water.json")){
            Log.d("onCreate", "Checking if water.json exists")
            migrateOldWaterData(this, "water.json")
            }
            else {
                initializeWaterFile(this, "water.json")
            }

        } catch (e: Exception) {
            Log.e("onCreate", "Error during initialization", e)
        }

        enableEdgeToEdge()
        setContent {
            AppThemeWrapper{
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CallorEaseTheme {
        Greeting("Android")
    }
}