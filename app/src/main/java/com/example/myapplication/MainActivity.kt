package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class AppItem(
    val name: String,
    val description: String,
    val category: String,
    val icon: String,
    val color: Color
)

val applications = listOf(
    AppItem(
        "YouTube", "Просмотр видео и каналов",
        "Развлечения", "Y", Color(0xFFFF0033)
    ),
    AppItem(
        "Telegram", "Сообщения и звонки",
        "Общение", "T", Color(0xFF229ED9)
    ),
    AppItem(
        "Spotify", "Музыка и подкасты",
        "Музыка", "S", Color(0xFF1DB954)
    ),
    AppItem(
        "Google Maps", "Карты и навигация",
        "Путешествия", "M", Color(0xFF4285F4)
    ),
    AppItem(
        "Gmail", "Электронная почта",
        "Работа", "G", Color(0xFFEA4335)
    ),
    AppItem(
        "Duolingo", "Изучение иностранных языков",
        "Образование", "D", Color(0xFF58CC02)
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                AppCatalogScreen()
            }
        }
    }
}

@Composable
fun AppCatalogScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F5F7))
    ) {
        AppToolbar()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Популярные приложения",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202124)
                )

                Spacer(modifier = Modifier.height(4.dp))
            }

            items(applications) { app ->
                AppCard(app)
            }
        }
    }
}

@Composable
fun AppToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF6750A4))
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "A",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6750A4)
            )
        }

        Column(
            modifier = Modifier.padding(start = 14.dp)
        ) {
            Text(
                text = "App Catalog",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Каталог приложений",
                fontSize = 13.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun AppCard(app: AppItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        app.color,
                        RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = app.icon,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {
                Text(
                    text = app.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202124)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = app.description,
                    fontSize = 14.sp,
                    color = Color(0xFF5F6368)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = app.category,
                    fontSize = 12.sp,
                    color = Color(0xFF6750A4),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(
                            Color(0xFFEDE7F6),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                )
            }
        }
    }
}
