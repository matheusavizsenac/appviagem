package com.example.trabalho_aplicativo_viagem.dao

import androidx.room.*
import com.example.trabalho_aplicativo_viagem.entity.Travel


@Dao
interface TravelDao {

    @Insert
    fun insert(travel: Travel)

    @Update
    suspend fun update(travel: Travel)

    @Delete
    suspend fun delete(travel: Travel)

    @Query("select * from travel t")
    suspend fun getAll(): List<Travel>

    @Query("select * from travel t where t.destination = :destination")
    suspend fun findByDestination(destination: String): Travel

    @Query("select * from travel t where t.user_id = :user_id")
    suspend fun findById(user_id: Int):  List<Travel>

}
