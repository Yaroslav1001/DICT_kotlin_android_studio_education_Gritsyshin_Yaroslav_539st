package com.example.practice_7

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TaxCalculatorApp()
        }
    }
}
@Composable
fun TaxCalculatorApp() {
    // Створюємо стани для зберігання суми рахунку та відсотка податку
    val billAmount = remember { mutableDoubleStateOf(0.0) }
    val taxPercentage = remember { mutableIntStateOf(0) }

    // Створюємо вертикальний стовпець, у якому розташовуємо компоненти користувацького інтерфейсу
    Column(
        modifier = Modifier
            .fillMaxSize() // Займаємо максимально можливий простір
            .padding(16.dp), // Додаємо відступи
        verticalArrangement = Arrangement.spacedBy(16.dp) // Відстань між компонентами
    ) {
        // Текстове поле для введення суми рахунку
        OutlinedTextField(
            value = billAmount.doubleValue.toString(), // Відображаємо значення суми рахунку в текстовому полі
            onValueChange = {
                // Оновлюємо значення суми рахунку, перетворюючи рядок на число. Якщо рядок не є числом, встановлюємо значення за замовчуванням
                billAmount.doubleValue = it.toDoubleOrNull() ?: 0.0
                            },
            label = { Text("Enter the amount of the invoice") }, // Мітка над текстовим полем
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Тип клавіатури - числова
            modifier = Modifier.fillMaxWidth() // Займаємо всю доступну ширину
        )

        // Слайдер для вибору відсотка податку
        Slider(
            value = taxPercentage.intValue.toFloat(), // Встановлюємо поточне значення слайдера
            onValueChange = {
                // Оновлюємо значення відсотка податку, перетворюючи число на ціле значення
                taxPercentage.intValue = it.toInt()
                            },
            valueRange = 0f..100f, // Діапазон можливих значень для слайдера
            steps = 0.1f.toInt(), // Шаг слайдера
            modifier = Modifier.fillMaxWidth() // Займаємо всю доступну ширину
        )

        // Текстова мітка з інформацією про суму рахунку, відсоток податку та суму податку
        Text(
            text = "Invoice amount: ${billAmount.doubleValue}$ tax percentage: ${taxPercentage.intValue}% Tax amount: ${
                calculateTip(
                    billAmount.doubleValue,
                    taxPercentage.intValue
                )
            }$", // Формуємо текст мітки за допомогою шаблонних рядків
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

// Функція для обчислення суми податку
fun calculateTip(billAmount: Double, taxPercentage: Int): Double {
    return billAmount * taxPercentage / 100.0
}
