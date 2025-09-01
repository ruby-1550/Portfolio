package com.example.mortgagecalculator

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.mortgagecalculator.screens.House


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.mortgagecalculator.ui.theme.MortgageCalculatorTheme
import androidx.compose.ui.Modifier

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.mortgagecalculator.ui.theme.MortgageCalculatorTheme


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mortgagecalculator.screens.MortgageCalculatorScreen


import com.example.mortgagecalculator.screens.HomeDetailsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MortgageCalculatorTheme {
                // A surface container using the background color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "signIn") {
                        composable("signIn") {
                            SignInScreen(onSignInSuccess = {
                                // Navigate to the home screen after successful sign-in
                                navController.navigate("home") {
                                    // Clear the back stack so the user can't navigate back to the sign-in screen
                                    popUpTo("signIn") { inclusive = true }
                                }
                            })
                        }
                        composable("home") {
                            HomeDetailsScreen(
                                houses = listOf(
                                    House(price = "$300,000", description = "3 bed, 2 bath, 1,800 sq ft"),
                                    House(price = "$450,000", description = "4 bed, 3 bath, 2,400 sq ft"),
                                    House(price = "$600,000", description = "5 bed, 4 bath, 3,000 sq ft")
                                ),
                                onSelectHouse = { house ->
                                    navController.navigate("mortgageCalculator/${house.price}/${house.description}")
                                }
                            )
                        }
                        composable("mortgageCalculator/{price}/{description}") { backStackEntry ->
                            val price = backStackEntry.arguments?.getString("price") ?: ""
                            val description = backStackEntry.arguments?.getString("description") ?: ""
                            MortgageCalculatorScreen(house = House(price, description))
                        }
                    }
                }
            }
        }
    }
}