package com.example.trabalho_aplicativo_viagem.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalho_aplicativo_viagem.entity.Travel
import com.example.trabalho_aplicativo_viagem.repository.TravelRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterNewTravelViewModel(private val travelRepository: TravelRepository): ViewModel() {

    var destination by mutableStateOf("")
    var classification by mutableStateOf("")
    var begin by mutableStateOf("")
    var end by mutableStateOf("")
    var user_id = Int


    var isDestinationValido by mutableStateOf(true)
    var isClassificationValido by mutableStateOf(true)
    var isBeginValido by mutableStateOf(true)
    var isEndValido by mutableStateOf(true)


    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validateFields() {
        isDestinationValido = destination.isNotEmpty()
        if (!isDestinationValido) {
            throw Exception("Insira o destino")
        }
        isClassificationValido = classification.isNotEmpty()
        if (!isClassificationValido) {
            throw Exception("Insira a classificacao")
        }
        isBeginValido = begin.isNotEmpty()
        if (!isBeginValido) {
            throw Exception("Insira a data de inicio")
        }
        isEndValido = end.isNotEmpty()
        if (!isEndValido) {
            throw Exception("Insira a data de fim")
        }

    }

    fun registerNewTravel(user_id: Int, onSuccess: () -> Unit) {
        try {
            validateFields()
            val newTravel = Travel(destination = destination, classification = classification, begin = begin, end = end, user_id = user_id)
            travelRepository.addTravel(newTravel)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }

    }

}