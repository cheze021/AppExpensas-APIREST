package com.example.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 0, 70.0, "GROCERIES", "Weekly buy"),
    Expense(id = 1, 45.5, "PARTY", "Birthday party"),
    Expense(id = 2, 12.0, "SNACKS", "Afternoon snack"),
    Expense(id = 3, 5.5, "COFFEE", "Morning coffee"),
    Expense(id = 4, 150.0, "CAR", "Gas refill"),
    Expense(id = 5, 1200.0, "HOUSE", "Rent payment"),
    Expense(id = 6, 30.0, "OTHER", "Random purchase")
)

var lastExpense = expenses.size.toLong()

@Serializable
data class Expense (
    val id: Long = lastExpense++,
    val amount: Double,
    val categoryName: String,
    val description: String
)
