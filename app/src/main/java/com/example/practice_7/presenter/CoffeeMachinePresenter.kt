package com.example.practice_7.presenter

import com.example.practice_7.model.CoffeeMachine
import com.example.practice_7.model.Resources

class CoffeeMachinePresenter(
    private val view: CoffeeMachineContract.View,
    private val coffeeMachine: CoffeeMachine
) : CoffeeMachineContract.Presenter {

    // Функція для купівлі кави
    override fun buyCoffee(type: String) {
        // Вызываем метод buy из CoffeeMachine
        val message = coffeeMachine.buy(type)
        // Отображаем сообщение пользователю
        view.showMessage(message)
        // Показываем оставшиеся ресурсы после покупки
//        showRemaining()
    }

    // Функція для поповнення інгредієнтів
    override fun fillIngredients(addResources: Resources) {
        val message = coffeeMachine.fill(addResources)
        view.showMessage(message)
        // Викликаємо метод інтерфейсу view для отримання введення від користувача
//        showRemaining()
    }

    // Функція для вилучення грошей
    override fun takeMoney() {
        val message = coffeeMachine.take()
        // Відображаємо результат через інтерфейс view
        view.showMessage(message)
    }

//    // Функція для показу залишкових ресурсів
//    override fun showRemaining() {
//        val remaining = coffeeMachine.remaining()
//        // Відображаємо результат через інтерфейс view
//        view.showMessage(remaining)
//    }

    // Метод для получения информации о ресурсах
    fun getRemainingInfo(): String {
        return coffeeMachine.remaining()
    }
}

