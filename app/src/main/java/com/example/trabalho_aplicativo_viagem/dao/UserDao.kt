package com.example.trabalho_aplicativo_viagem.dao

import androidx.room.*
import com.example.trabalho_aplicativo_viagem.entity.User

interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("select * from user u order by u.name")
    suspend fun getAll(): List<User>

    @Query("select * from user u where u.name = :name")
    suspend fun findByName(name: String): User?
}