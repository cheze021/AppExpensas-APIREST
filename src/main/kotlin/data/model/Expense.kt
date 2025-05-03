package com.example.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 1, 70.0, "GROCERIES", "Weekly buy", "2024-12-10"),
    Expense(id = 2, 45.5, "PARTY", "Birthday party", "2024-12-10"),
    Expense(id = 3, 12.0, "SNACKS", "Afternoon snack", "2024-12-10"),
    Expense(id = 4, 5.5, "COFFEE", "Morning coffee", "2024-12-10"),
    Expense(id = 5, 150.0, "CAR", "Gas refill", "2024-12-10"),
    Expense(id = 6, 1200.0, "HOUSE", "Rent payment", "2024-12-10"),
    Expense(id = 7, 30.0, "OTHER", "Random purchase", "2024-12-10")
)

val expensesCategories: List<String> = mutableListOf(
    "GROCERIES",
    "PARTY",
    "SNACKS",
    "COFFEE",
    "CAR",
    "HOUSE",
    "OTHER",
)

@Serializable
data class Expense (
    val id: Long = -1,
    val amount: Double,
    val categoryName: String,
    val description: String,
    val date: String
)
