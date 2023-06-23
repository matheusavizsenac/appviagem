package com.example.trabalho_aplicativo_viagem.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trabalho_aplicativo_viagem.database.AppDatabase
import com.example.trabalho_aplicativo_viagem.repository.UserRepository

class ListUserViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).userDao()
        val userRepository = UserRepository(dao)
        return ListUserViewModel(userRepository) as T
    }

}