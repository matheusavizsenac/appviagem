package com.example.trabalho_aplicativo_viagem.repository

import com.example.trabalho_aplicativo_viagem.dao.UserDao
import com.example.trabalho_aplicativo_viagem.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {
    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addUser(user: User) {
        coroutine.launch(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    fun delete(user: User) {
        coroutine.launch(Dispatchers.IO) {
            userDao.delete(user)
        }
    }

    suspend fun loadAllUsers(): List<User> {
        return userDao.getAll()
    }

    suspend fun findByName(name: String): User? =
        userDao.findByName(name)
}