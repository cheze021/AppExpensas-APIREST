package com.example.utils

import com.example.data.model.*

fun validateExpense(expense: Expense, categories: List<String>): Boolean {
    when {
        expense.amount < 0 -> {
            return false
        }

        expense.categoryName !in categories -> {
            return false
        }
    }

    return true
}