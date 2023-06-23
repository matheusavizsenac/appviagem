package com.example.trabalho_aplicativo_viagem.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trabalho_aplicativo_viagem.database.AppDatabase
import com.example.trabalho_aplicativo_viagem.repository.TravelRepository

class NewTravelViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).TravelDao()
        val travelRepository = TravelRepository(dao)
        return RegisterNewTravelViewModel(travelRepository) as T
    }

}