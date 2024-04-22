package com.example.practice_7.view

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_7.R
import com.example.practice_7.model.CoffeeMachine
import com.example.practice_7.presenter.CoffeeMachineContract
import com.example.practice_7.presenter.CoffeeMachinePresenter
import com.example.practice_7.model.Resources

class MainActivity : AppCompatActivity(), CoffeeMachineContract.View {

    private lateinit var presenter: CoffeeMachineContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = CoffeeMachinePresenter(this, CoffeeMachine(Resources()))

        // Додати обробник подій для кнопок
        findViewById<Button>(R.id.orderCoffeeButton).setOnClickListener {
            // Отобразить диалоговое окно с вариантами выбора кофе
            val coffeeOptions = arrayOf("espresso", "latte", "cappuccino")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Coffee Type")
            builder.setItems(coffeeOptions) { _, which ->
                // Вызвать метод buyCoffee с выбранным типом кофе
                (presenter as CoffeeMachinePresenter).buyCoffee(coffeeOptions[which])
                // После покупки вызываем обновление информации о ресурсах
                updateResourcesInfo()
            }
            builder.show()
        }

        findViewById<Button>(R.id.fillResourcesButton).setOnClickListener {
            try {
                val waterInput = findViewById<EditText>(R.id.waterInput).text.toString().toInt()
                val milkInput = findViewById<EditText>(R.id.milkInput).text.toString().toInt()
                val coffeeBeansInput = findViewById<EditText>(R.id.coffeeBeansInput).text.toString().toInt()
                val disposableCupsInput = findViewById<EditText>(R.id.disposableCupsInput).text.toString().toInt()

                val resources = Resources(waterInput, milkInput, coffeeBeansInput, disposableCupsInput)
                (presenter as CoffeeMachinePresenter).fillIngredients(resources)
                // После покупки вызываем обновление информации о ресурсах
                updateResourcesInfo()
            } catch (e: NumberFormatException) {
                // Если возникла ошибка преобразования, выведите сообщение об ошибке
                showMessage("Invalid input! Please enter valid numbers.")
            }
        }


        findViewById<Button>(R.id.collectMoneyButton).setOnClickListener {
            // Викликати метод takeMoney()
            presenter.takeMoney()
            // После покупки вызываем обновление информации о ресурсах
            updateResourcesInfo()
        }
        // Вызываем функцию после создания активности для инициализации информации о ресурсах
        updateResourcesInfo()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Метод для обновления информации о ресурсах на экране
    private fun updateResourcesInfo() {
        val remainingInfo = (presenter as CoffeeMachinePresenter).getRemainingInfo()
        findViewById<TextView>(R.id.resourcesInfoTextView).text = remainingInfo
    }

}
