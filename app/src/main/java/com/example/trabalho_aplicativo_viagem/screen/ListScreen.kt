package com.example.trabalho_aplicativo_viagem.screen

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabalho_aplicativo_viagem.viewModel.ListTravelModelFactory
import com.example.trabalho_aplicativo_viagem.viewModel.ListTravelViewModel

@Composable
fun ListScreen(userId: Int, OpenNewTravel: (Int) -> Unit, listExpenses:(Int) -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListTravelViewModel = viewModel(
        factory = ListTravelModelFactory (application)
    )


    viewModel.loadAllTravels(userId)


    Column(Modifier.fillMaxSize()) {
        Button(
            onClick = {
                OpenNewTravel(userId)
            }) {
            Text(text = "Adicionar nova viagem")
        }
        LazyColumn() {
            items(items = viewModel.travels.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row( modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "${it.destination}",
                        )
                        Spacer(Modifier.weight(1f))

                        Button(
                            onClick = {
                                listExpenses(it.id)
                            }) {
                            Text(text = "Adicionar despesas")
                        }

                    }

                }
            }
        }

    }

}