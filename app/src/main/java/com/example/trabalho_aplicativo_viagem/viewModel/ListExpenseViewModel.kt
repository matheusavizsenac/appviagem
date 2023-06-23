package com.example.trabalho_aplicativo_viagem.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalho_aplicativo_viagem.entity.Expense
import com.example.trabalho_aplicativo_viagem.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ListExpenseViewModel (private val expenseRepository: ExpenseRepository): ViewModel() {
    var expenses: MutableState<List<Expense>> = mutableStateOf(listOf())

    fun loadAllExpenses(travelId: Int) {
        viewModelScope.launch {
            expenses.value = expenseRepository.findByTravel(travelId)
        }
    }
}