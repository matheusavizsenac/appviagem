package com.example.trabalho_aplicativo_viagem.repository

import com.example.trabalho_aplicativo_viagem.dao.ExpenseDao
import com.example.trabalho_aplicativo_viagem.entity.Expense
import com.example.trabalho_aplicativo_viagem.entity.Travel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseRepository (private val expenseDao : ExpenseDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addExpense(expense: Expense) {
        coroutine.launch(Dispatchers.IO) {
            expenseDao.insert(expense)
        }
    }
    suspend fun findByTravel(travelId: Int): List<Expense> =
        expenseDao.getValor(travelId)

    fun delete(expense: Expense) {
        coroutine.launch(Dispatchers.IO) {
            expenseDao.delete(expense)
        }
    }

}