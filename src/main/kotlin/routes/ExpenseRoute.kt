package com.example.routes

import com.example.data.model.MessageResponse
import com.example.data.model.Expense
import com.example.data.model.expenses
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.expensesRouting() {

    // GET
    get("/expenses") {
        if(expenses.isEmpty()) {
            call.respondText { "No expenses found" }
        } else {
            call.respond(status = HttpStatusCode.OK, expenses)
        }
    }

    // GET BY ID
    get("/expenses/{id}"){
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = expenses.find{ it.id == id }
        if(id == null || expense == null){
            call.respond(status = HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@get
        }
        call.respond(status = HttpStatusCode.OK, expenses[id.toInt()])
    }

    // POST
    post("/expenses") {
        val expense = call.receive<Expense>()
        val maxId = expenses.maxOf{ it.id } + 1
        expenses.add(expense.copy(id = maxId))
        call.respond(status = HttpStatusCode.OK, MessageResponse("Expense successfully added!"))
    }

    // PUT
    put("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = call.receive<Expense>()
        if(id == null || id !in 0 until expenses.size){
            call.respond(status = HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@put
        }
        val index = expenses.indexOfFirst { it.id == id }
        expenses[index] = expense.copy(id = id)
        call.respond(HttpStatusCode.OK, MessageResponse("Expense successfully updated!"))
    }

    // DELETE
    delete("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = expenses.find{ it.id == id }
        if(id == null || expense == null){
            call.respond(status = HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@delete
        }
        expenses.removeIf { it.id == id}
        call.respond(HttpStatusCode.OK, MessageResponse("Expense successfully updated!"))
    }

}