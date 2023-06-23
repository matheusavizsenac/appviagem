package com.example.trabalho_aplicativo_viagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trabalho_aplicativo_viagem.screen.AboutScreen
import com.example.trabalho_aplicativo_viagem.screen.ExpenseListScreen
import com.example.trabalho_aplicativo_viagem.screen.FormScreen
import com.example.trabalho_aplicativo_viagem.screen.ListScreen
import com.example.trabalho_aplicativo_viagem.screen.LoginScreen
import com.example.trabalho_aplicativo_viagem.screen.NewExpense
import com.example.trabalho_aplicativo_viagem.screen.NewTravel
import com.example.trabalho_aplicativo_viagem.ui.theme.TrabalhoaplicativoviagemTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabalhoaplicativoviagemTheme {
                androidx.compose.material.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material.MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                    onClick = { navController.navigate("form") }) {
                    androidx.compose.material.Text(text = "Add")
                }
                Spacer(modifier = Modifier.size(50.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                    onClick = { navController.navigate("login") }) {
                    androidx.compose.material.Text(text = "Login")
                }
                Spacer(modifier = Modifier.size(50.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                    onClick = { navController.navigate("about") }) {
                    androidx.compose.material.Text(text = "About")
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            NavHost(
                navController = navController,
                startDestination = "login" ) {

                composable("login") {
                    LoginScreen(
                        onBack = {
                            navController.navigateUp()
                        },
                        onAfterLogin = { user_id ->
                            navController.navigate("list/$user_id")
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Login ok"
                                )
                            }
                        }
                    )
                }
                composable("about") {
                    AboutScreen()
                }

                composable(
                    "list/{user_id}",
                    arguments = listOf(navArgument("user_id") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("user_id")
                    val user_id = param?.toInt()
                    if (user_id != null) {
                        ListScreen(
                            user_id,
                            OpenNewTravel = { user_id ->
                                navController.navigate("new_travel/$user_id")
                            },
                            listExpenses = {travel_id ->
                                navController.navigate("list_expense/$travel_id")

                            }
                        )
                    }
                }
                composable(
                    "list_expense/{travel_id}",
                    arguments = listOf(navArgument("travel_id") { type = NavType.StringType })
                ){
                    val param = it.arguments?.getString("travel_id")
                    val travel_id = param?.toInt()
                    if (travel_id != null) {
                        ExpenseListScreen(
                            travel_id,
                            OpenNewExpense = {travel_id ->
                                navController.navigate("new_expense/$travel_id")
                            }
                        )
                    }
                }
                composable(
                    "new_expense/{travel_id}",
                    arguments = listOf(navArgument("travel_id") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("travel_id")
                    val travel_id = param?.toInt()
                    if (travel_id != null) {
                        NewExpense(
                            travel_id,
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

                composable(
                    "new_travel/{user_id}",
                    arguments = listOf(navArgument("user_id") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("user_id")
                    val userId = param?.toInt()
                    if (userId != null) {
                        NewTravel(
                            userId,
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

                composable("form") {
                    FormScreen(onAfterSave = {
                        navController.navigateUp()
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Usuario registrado"
                            )
                        }
                    },
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TrabalhoaplicativoviagemTheme {
        MyApp()
    }
}