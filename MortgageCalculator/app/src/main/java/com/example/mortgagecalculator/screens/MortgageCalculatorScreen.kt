// MortgageCalculatorScreen.kt
package com.example.mortgagecalculator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.pow

// Import House from HouseData.kt
import com.example.mortgagecalculator.screens.House

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text


import com.example.mortgagecalculator.ui.theme.MortgageCalculatorTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*

import androidx.compose.runtime.remember

import androidx.compose.foundation.Image

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.*
import com.example.mortgagecalculator.R

@Composable
fun MortgageCalculatorScreen(house: House) {
    val downPayment = remember { androidx.compose.runtime.mutableStateOf("") }
    val loanAmount = remember { androidx.compose.runtime.mutableStateOf("") }
    val loanDuration = remember { androidx.compose.runtime.mutableStateOf("") }
    val result = remember { androidx.compose.runtime.mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.calculate_background),
            contentDescription = null, // decorative image
            contentScale = ContentScale.Crop,


        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 4.dp) // Add bottom padding to move up from the bottom
                .padding(top = 400.dp)
        ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .align(Alignment.BottomCenter), // Align the content to the bottom of the screen
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Mortgage Calculator for ${house.price}")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = downPayment.value,
            onValueChange = { downPayment.value = it },
            label = { Text("Down Payment") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loanAmount.value,
            onValueChange = { loanAmount.value = it },
            label = { Text("Loan Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loanDuration.value,
            onValueChange = { loanDuration.value = it },
            label = { Text("Loan Duration (Months)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            result.value = calculateMortgage(
                downPayment.value.toDoubleOrNull() ?: 0.0,
                loanAmount.value.toDoubleOrNull() ?: 0.0,
                loanDuration.value.toIntOrNull() ?: 0
            )
        }) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result.value?.let {
            Text(text = "Monthly Payment: $it")
        }
    }
}}}

fun calculateMortgage(downPayment: Double, loanAmount: Double, loanDuration: Int): String {
    val interestRate = 0.05 // Example interest rate
    val monthlyRate = interestRate / 12
    val totalLoan = loanAmount - downPayment
    val months = loanDuration * 12
    val monthlyPayment = totalLoan * monthlyRate / (1 - (1 + monthlyRate).pow(-months))
    return "%.2f".format(monthlyPayment)
}

@Preview(showBackground = true)
@Composable
fun MortgageCalculatorScreenPreview() {
    MortgageCalculatorScreen(
        house = House(price = "$200,000", description = "A beautiful house")
    )
}
