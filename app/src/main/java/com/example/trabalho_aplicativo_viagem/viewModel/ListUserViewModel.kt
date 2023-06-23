package com.example.trabalho_aplicativo_viagem.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalho_aplicativo_viagem.entity.User
import com.example.trabalho_aplicativo_viagem.repository.UserRepository
import kotlinx.coroutines.launch

class ListUserViewModel(private val userRepository: UserRepository): ViewModel() {

    var users: MutableState<List<User>> = mutableStateOf(listOf())

    var userForDelete: User? by mutableStateOf(null)

    fun loadAllUsers(){
        viewModelScope.launch {
            users.value = userRepository.loadAllUsers()
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            userForDelete?.let {
                userRepository.delete(it)
                loadAllUsers()
            }
        }
    }
}