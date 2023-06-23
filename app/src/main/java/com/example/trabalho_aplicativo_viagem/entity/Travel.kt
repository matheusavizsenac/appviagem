package com.example.trabalho_aplicativo_viagem.entity
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "travel")
data class Travel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val destination: String,
    val classification: String,
    val begin: String,
    val end: String,
    val user_id: Int
) {
}