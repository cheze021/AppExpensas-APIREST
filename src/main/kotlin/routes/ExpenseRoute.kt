package com.example.routes

import com.example.data.model.MessageResponse
import com.example.data.model.Expense
import com.example.data.model.expenses
import com.example.data.model.expensesCategories
import com.example.utils.validateExpense
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.expensesRouting() {

    // GET
    get("/expenses") {
        call.respond(status = HttpStatusCode.OK, expenses)
    }

    // GET BY ID
    get("/expenses/{id}"){
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = expenses.find{ it.id == id }
        if(id == null || expense == null){
            call.respond(status = HttpStatusCode.NotFound, MessageResponse("Expense not found."))
            return@get
        }
        call.respond(status = HttpStatusCode.OK, expense)
    }

    // POST
    post("/expenses") {
        val expense = call.receive<Expense>()
        if(validateExpense(expense, expensesCategories)) {
            val lastExpenseId = if(expenses.isEmpty()) {
                expense.id
            } else {
                expenses.maxOf{ it.id } + 1
            }
            expenses.add(expense.copy(id = lastExpenseId))
            call.respond(status = HttpStatusCode.OK, MessageResponse("Expense successfully added!"))
        } else {
            call.respond(status = HttpStatusCode.BadRequest, MessageResponse("Expense is not valid. Please try again."))
        }

    }

    // PUT
    put("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = call.receive<Expense>()
        if(validateExpense(expense, expensesCategories)) {
            if(id == null || id !in 0 until expenses.size){
                call.respond(status = HttpStatusCode.NotFound, MessageResponse("Expense not found."))
                return@put
            }
            val index = expenses.indexOfFirst { it.id == id }
            expenses[index] = expense.copy(id = id)
            call.respond(HttpStatusCode.OK, MessageResponse("Expense successfully updated!"))
        } else {
            call.respond(HttpStatusCode.BadRequest, MessageResponse("Expense is not valid. Please try again."))
        }

    }

    // DELETE
    delete("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = expenses.find{ it.id == id }
        if(id == null || expense == null){
            call.respond(status = HttpStatusCode.NotFound, MessageResponse("Expense not found."))
            return@delete
        }
        expenses.removeIf { it.id == id}
        call.respond(HttpStatusCode.OK, MessageResponse("Expense successfully deleted!"))
    }

}