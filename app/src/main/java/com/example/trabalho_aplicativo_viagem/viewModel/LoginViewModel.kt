package com.example.trabalho_aplicativo_viagem.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalho_aplicativo_viagem.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    var name by mutableStateOf("")
    var password by mutableStateOf("")

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun validateLogin(onResult: (Int) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.findByName(name)
            val result = user != null && user.password == password
            if (user != null) {
                onResult(user.id)
            }
            if (!result)
                _toastMessage.emit("Login Invalido")
        }

    }
}
