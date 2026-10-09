package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                MainScreen(
                    onOpenSecond = { text ->
                        val intent = Intent(this, SecondActivity::class.java)
                        intent.putExtra("message", text)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(onOpenSecond: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Практическое задание 3",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Текст или номер телефона") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Button(
            onClick = {
                if (text.isBlank()) {
                    Toast.makeText(
                        context, "Введите текст", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    onOpenSecond(text)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            onClick = {
                val phone = text.trim()

                if (!phone.matches(Regex("""\+?[0-9 ()-]{5,20}"""))) {
                    Toast.makeText(
                        context, "Введите корректный номер телефона",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val cleanPhone = phone.replace(Regex("[ ()-]"), "")
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:$cleanPhone")
                    context.startActivity(intent)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = {
                if (text.isBlank()) {
                    Toast.makeText(
                        context, "Введите текст для отправки",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }

                    context.startActivity(
                        Intent.createChooser(intent, "Поделиться через")
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}
