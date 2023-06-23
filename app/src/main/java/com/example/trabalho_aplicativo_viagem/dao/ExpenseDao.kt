package com.example.trabalho_aplicativo_viagem.dao

import androidx.room.*
import com.example.trabalho_aplicativo_viagem.entity.Expense

@Dao
interface  áExpenseDao {

    @Insert
    fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("select * from expense e where e.travel_id = :travelId")
    suspend fun getValor(travelId: Int): List<Expense>

}