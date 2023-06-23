package com.example.trabalho_aplicativo_viagem.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trabalho_aplicativo_viagem.database.AppDatabase
import com.example.trabalho_aplicativo_viagem.repository.ExpenseRepository

class ListExpenseModelFactory (val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).ExpenseDao()
        val expenseRepository = ExpenseRepository(dao)
        return ListExpenseViewModel(expenseRepository) as T
    }
}