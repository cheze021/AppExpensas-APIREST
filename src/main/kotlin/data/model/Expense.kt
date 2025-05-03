package com.example.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 1, 1000.00, "LUZ", "UTE", "2024-12-10"),
    Expense(id = 2, 750.00, "INTERNET", "Antel", "2024-12-10"),
    Expense(id = 3, 500.00, "SUPER", "Surtidos", "2024-12-10"),
    Expense(id = 4, 13650.00, "ALQUILER", "Alquiler apto", "2024-12-10"),
    Expense(id = 5, 350.00, "MERIENDAS", "Merienda en ....", "2024-12-10"),
    Expense(id = 6, 750.00, "PEDIDOS_YA", "Deliverys de comida", "2024-12-10"),
    Expense(id = 7, 350.00, "OTROS", "Compra random", "2024-12-10")
)

val expensesCategories: List<String> = mutableListOf(
    "LUZ",
    "INTERNET",
    "SUPER",
    "ALQUILER",
    "MERIENDAS",
    "PEDIDOS_YA",
    "OTROS",
)

@Serializable
data class Expense (
    val id: Long = -1,
    val amount: Double,
    val categoryName: String,
    val description: String,
    val date: String
)
