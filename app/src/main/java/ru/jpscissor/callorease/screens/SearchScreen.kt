package ru.jpscissor.callorease.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SearchScreen(onBack: () -> Unit, onProductSelect: () -> Unit) {

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

                Card(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
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
                        Spacer(Modifier.weight(1f))
                        Text(
                            "Поиск",
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(Modifier.weight(1f))
                    }
                }

                Spacer(Modifier.height(45.dp))

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
                    colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.onBackground ),
                    shape = RoundedCornerShape(15.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        BigProduct("Red Bull", 349, onProductSelect)
                    }

                }

            }


        }
    }
}


//data class Product(val name: String, val weight: String)
//
//
//@Composable
//fun ProductsList(products: List<Product>) {
//
//}


@Composable
fun BigProduct(name: String, weight: Int, onSelect: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { onSelect()}
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


@Preview
@Composable
fun PrevSearchScreen () {
    AppThemeWrapper {
        SearchScreen({}, {})
    }
}