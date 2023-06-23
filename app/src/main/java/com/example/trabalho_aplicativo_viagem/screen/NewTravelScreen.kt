package com.example.trabalho_aplicativo_viagem.screen

import android.app.Application
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabalho_aplicativo_viagem.viewModel.NewTravelViewModelFactory
import com.example.trabalho_aplicativo_viagem.viewModel.RegisterNewTravelViewModel
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date

@Composable
fun NewTravel(userId: Int, onBack: () -> Unit)  {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewTravelViewModel = viewModel(
        factory = NewTravelViewModelFactory(application)
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

    viewModel.classification = "L"

    val focusManager = LocalFocusManager.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialogStart= DatePickerDialog(
        ctx,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.begin = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )
    val mDatePickerDialogEnd = DatePickerDialog(
        ctx,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.end = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    fun openDatePicker(focused: Boolean, date: String) {
        if (focused && date == "inicio") {
            mDatePickerDialogStart.show();
        }
        if (focused && date == "fim"){
            mDatePickerDialogEnd.show()
        }
    }

    Scaffold(scaffoldState = scaffoldState ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.destination,
                onValueChange = { viewModel.destination = it},
                label = {
                    Text(text = "Destino")
                }
            )

            Row() {
                RadioButton(
                    selected = viewModel.classification == "L",
                    onClick = {viewModel.classification = "L"}
                )
                Text(
                    text = "L",
                    modifier = Modifier.padding(start = 2.dp)
                )
            }

            Row() {
                RadioButton(
                    selected = viewModel.classification == "N",
                    onClick = {viewModel.classification = "N"}
                )
                Text(
                    text = "N",
                    modifier = Modifier.padding(start = 1.dp)
                )
            }

            OutlinedTextField(
                value = viewModel.begin,
                onValueChange = {},
                label = {
                    Text(text = "Data de Inicio")
                },
                modifier = Modifier.onFocusChanged { a -> openDatePicker(a.isFocused, "inicio") }
            )
            OutlinedTextField(
                value = viewModel.end,
                onValueChange = {},
                label = {
                    Text(text = "Data de Fim")
                },
                modifier = Modifier.onFocusChanged { b -> openDatePicker(b.isFocused, "fim") }
            )
            Row() {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                    onClick = {
                    focusManager.clearFocus()
                    viewModel.registerNewTravel(userId, onSuccess = {
                        onBack()
                    })
                }) {
                    Text(text = "Adicionar")
                }
            }

        }
    }
}