package ru.jpscissor.callorease.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.jpscissor.callorease.R
import ru.jpscissor.callorease.ui.theme.AppThemeWrapper
import ru.jpscissor.callorease.ui.theme.currentTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import android.content.Context
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import ru.jpscissor.callorease.data.Product
import ru.jpscissor.callorease.data.loadConsumedProductsFromFile
import ru.jpscissor.callorease.screens.CurrentProduct.cal
import ru.jpscissor.callorease.screens.CurrentProduct.carb
import ru.jpscissor.callorease.screens.CurrentProduct.fat
import ru.jpscissor.callorease.screens.CurrentProduct.name
import ru.jpscissor.callorease.screens.CurrentProduct.prot

object CurrentProduct{
    var name by mutableStateOf("")
    var prot by mutableDoubleStateOf(0.1)
    var fat by mutableDoubleStateOf(0.1)
    var carb by mutableDoubleStateOf(0.1)
    var cal by mutableDoubleStateOf(0.1)
}

@Composable
fun SearchScreen(onBack: () -> Unit, onProductSelect: () -> Unit, context: Context) {
    var query by remember { mutableStateOf("") }
    val products = remember { loadProductsFromAssets(context, "foodru.json") }
    val foundProducts = if (query.isEmpty()) emptyList() else searchProducts(query, products)

    var isFocused by remember { mutableStateOf(false) }


//    val recentProducts = remember { loadConsumedProductsFromFile(context, "recent_products.json").take(10) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(systemPaddingWithoutBottom())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            ApperPanel("Прием пищи", onBack)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                // Поле поиска
                Card(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = if (currentTheme() == 1) painterResource(R.drawable.search_green) else painterResource(R.drawable.search),
                            modifier = Modifier.size(25.dp),
                            contentDescription = ""
                        )

                        TextField(
                            value = query,
                            onValueChange = { query = it },
                            modifier = Modifier.fillMaxWidth().onFocusChanged { focusState ->
                                isFocused = focusState.isFocused
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp
                            ),
                            placeholder = {
                                if (!isFocused && query.isEmpty()) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Spacer(Modifier.weight(0.8f))
                                        Text(
                                            "Поиск",
                                            color = Color.LightGray,
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(Modifier.weight(1f))
                                    }
                                }

                            }
                        )

                    }
                }

                Spacer(Modifier.height(24.dp))

                // Недавние продукты или результаты поиска
                if (query.isEmpty()) {
                    Text(
                        text = "Недавние",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(Modifier.height(8.dp))
                    Card(
                        modifier = Modifier
                            .height(430.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
                        shape = RoundedCornerShape(15.dp)
                    ) {
//                        LazyColumn(modifier = Modifier.fillMaxSize()) {
//                            items(recentProducts) { consumedProduct ->
////                                BigProduct(
////                                    name = consumedProduct.product.name,
////                                    weight = consumedProduct.amount.toInt()
////                                ) {
//////                                    currentProductUpdate(consumedProduct)
////                                    onProductSelect()
////                                }
//                            }
//                        }
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f)) {
                        items(foundProducts) { product ->
                            ItemInSearch(
                                name = product.name, cals = product.calories.toInt()) {
                                currentProductUpdate(product)
                                onProductSelect()
                            }
                        }
                    }
                }
            }
        }
    }
}



fun loadProductsFromAssets(context: Context, filePath: String): List<Product> {
    return try {
        val jsonString = context.assets.open(filePath).bufferedReader().use { it.readText() }
        Json.decodeFromString<List<Product>>(jsonString)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}


@Composable
fun ItemInSearch(name: String, cals: Int, onSelect: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onSelect() }
    ) {
        Text(
            name,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.width(200.dp)
        )
        Spacer(Modifier.weight(1f))
        Text(
            "Ккал: $cals",
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Start
        )
    }

}


fun searchProducts(query: String, products: List<Product>): List<Product> {
    return products.filter { it.name.contains(query, ignoreCase = true) }
}

@Composable
fun BigProduct(name: String, weight: Int, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onSelect() }
    ) {
        Text(
            name,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(Modifier.weight(1f))
        Text(
            "$weight г",
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}


fun currentProductUpdate(product: Product) {

    name = product.name
    cal = product.calories
    prot = product.proteins
    fat = product.fats
    carb = product.carbs

}


@Preview
@Composable
fun PrevSearchScreen() {
    AppThemeWrapper {
        SearchScreen({}, {}, LocalContext.current)
    }
}