package com.example.trabalho_aplicativo_viagem.screen

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.trabalho_aplicativo_viagem.viewModel.RegisterNewUserViewModel
import com.example.trabalho_aplicativo_viagem.viewModel.RegisterNewUserViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FormScreen(onAfterSave: () -> Unit, onBack:() -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewUserViewModel = viewModel(
        factory = RegisterNewUserViewModelFactory(application)
    )
    val ctx = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Long
            )
        }
    }


    val focusManager = LocalFocusManager.current


    Scaffold(scaffoldState = scaffoldState ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it},
                isError = !viewModel.isNameValid,
                label = {
                    Text(text = "Nome")
                }
            )
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it},
                label = {
                    Text(text = "E-mail")
                }
            )
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it},
                label = {
                    Text(text = "Senha")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.registrar(onSuccess = {
                        onAfterSave()
                    })
                }) {
                    Text(text = "Salvar")
                }
                Spacer(modifier = Modifier.size(5.dp))
                Button(
                    onClick = {
                        onBack()
                    }) {
                    Text(text = "Voltar")
                }


            }

        }
    }

}