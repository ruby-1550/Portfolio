package com.example.wisetemplate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wisetemplate.ui.theme.WiseTemplateTheme

import androidx.compose.ui.text.input.KeyboardType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WiseTemplateTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { MainScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("calculator") { CalculatorScreen(navController) }
        composable("loanResult/{result}") { backStackEntry ->
            LoanResultScreen(result = backStackEntry.arguments?.getString("result") ?: "", navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 58.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 220.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        Toast.makeText(
                            context,
                            "Logged in as $username",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate("home")
                    } else {
                        Toast.makeText(
                            context,
                            "Please enter both username and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF95ADC9)
                )
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var isDepositSelected by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.cards),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .offset(y = 20.dp)
                .align(Alignment.Center)
                .clickable { navController.navigate("calculator") }
        )
        Image(
            painter = painterResource(id = R.drawable.categories),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 280.dp)
                .height(200.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "Your Balance",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 22.sp,
                color = Color.Gray,
            ),
            modifier = Modifier
                .padding(top = 200.dp)
                .offset(x = 34.dp)
        )
        Text(
            text = "Your Cards",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 22.sp,
                color = Color.Gray,
            ),
            modifier = Modifier
                .padding(top = 359.dp)
                .offset(x = 34.dp)
        )
        Text(
            text = "$1,700.57",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 40.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .padding(top = 240.dp)
                .offset(x = 34.dp)
        )
        Text(
            text = "Jon Doe",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 22.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .padding(top = 520.dp)
                .offset(x = 50.dp)
        )
        Text(
            text = "**** **** **** 1090",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 22.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .padding(top = 545.dp)
                .offset(x = 50.dp)
        )

        Row(
            modifier = Modifier
                .offset(x = 44.dp)
                .padding(top = 301.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Button(
                onClick = { isDepositSelected = true },
                modifier = Modifier.width(140.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF95ADC9)
                )
            ) {
                Text(text = "Deposit")
            }
            Button(
                onClick = { isDepositSelected = false },
                modifier = Modifier.width(140.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF95ADC9)
                )
            ) {
                Text(text = "Withdrawal")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(navController: NavController) {
    var principal by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var monthlyPayment by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.calculator_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .offset(x = 10.dp, y = 815.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.home_icon),
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .offset(x = 182.dp, y = 815.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Text(
            text = "Finance Calculator",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 40.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 170.dp)
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = principal,
                onValueChange = { principal = it },
                label = { Text("Principal Amount") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )

            TextField(
                value = interestRate,
                onValueChange = { interestRate = it },
                label = { Text("Annual Interest Rate (%)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )

            TextField(
                value = monthlyPayment,
                onValueChange = { monthlyPayment = it },
                label = { Text("Monthly Payment") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )

            Button(
                onClick = {
                    result = calculateLoanPayoffTime(principal, interestRate, monthlyPayment)
                    navController.navigate("loanResult/$result")
                },
                modifier = Modifier
                    .offset(y=10.dp)
                    .width(140.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF95ADC9))
            ) {
                Text(text = "Calculate", fontSize = 18.sp)
            }

            result?.let {
                Text(
                    text = "Months to Pay Off: $it",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

fun calculateLoanPayoffTime(principal: String, interestRate: String, monthlyPayment: String): String {
    try {
        val principalAmount = principal.toDouble()
        val annualInterestRate = interestRate.toDouble() / 100
        val monthlyPaymentAmount = monthlyPayment.toDouble()

        val monthlyInterestRate = annualInterestRate / 12

        if (monthlyPaymentAmount <= principalAmount * monthlyInterestRate) {
            return "The monthly payment is too low to pay off the loan."
        }

        val months = Math.log(monthlyPaymentAmount) - Math.log(monthlyPaymentAmount - principalAmount * monthlyInterestRate)
        val result = months / Math.log(1 + monthlyInterestRate)

        return "%.0f months".format(result)
    } catch (e: Exception) {
        return "Invalid input. Please check your values."
    }
}

@Composable
fun LoanResultScreen(result: String, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.calculator_screen), // Use appropriate image
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .offset(x = 10.dp, y = 815.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.home_icon),
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .offset(x = 182.dp, y = 815.dp)
                .clickable {
                    // Navigate to the home screen
                    navController.navigate("home") {
                        // Optionally, you can clear the back stack if you don't want the user to return to the LoanResultScreen
                        popUpTo("home") { inclusive = true }
                    }
                }
        )
        Text(
            text = "Loan Payoff Result",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 40.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 170.dp)
        )
        Text(
            text = result,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 24.sp,
                color = Color.Black,
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    WiseTemplateTheme {
        MainScreen(navController = rememberNavController())
    }
}
