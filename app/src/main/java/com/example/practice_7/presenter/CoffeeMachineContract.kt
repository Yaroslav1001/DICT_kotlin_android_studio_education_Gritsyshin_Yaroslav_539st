package com.example.practice_7.presenter

import com.example.practice_7.model.Resources

interface CoffeeMachineContract {
    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        // Функция для купівлі кави
        fun buyCoffee(type: String)
        // Функция для поповнення інгредієнтів
        fun fillIngredients(addResources: Resources)
        // Функція для вилучення грошей
        fun takeMoney()
        // Функція для показу залишкових ресурсів
//        fun showRemaining()
    }
}