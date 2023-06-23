package com.example.trabalho_aplicativo_viagem.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalho_aplicativo_viagem.entity.Travel
import com.example.trabalho_aplicativo_viagem.repository.TravelRepository
import kotlinx.coroutines.launch

class ListTravelViewModel (private val travelRepository: TravelRepository): ViewModel() {

    var travels: MutableState<List<Travel>> = mutableStateOf(listOf())

    fun loadAllTravels(userId: Int) {
        viewModelScope.launch {
            travels.value = travelRepository.findById(userId)
        }
    }
}
