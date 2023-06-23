package com.example.trabalho_aplicativo_viagem.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trabalho_aplicativo_viagem.dao.ExpenseDao
import com.example.trabalho_aplicativo_viagem.dao.TravelDao
import com.example.trabalho_aplicativo_viagem.dao.UserDao
import com.example.trabalho_aplicativo_viagem.entity.Travel
import com.example.trabalho_aplicativo_viagem.entity.User

@Database(entities = [User::class, Travel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun TravelDao(): TravelDao
    abstract fun ExpenseDao(): ExpenseDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "my-db3"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}