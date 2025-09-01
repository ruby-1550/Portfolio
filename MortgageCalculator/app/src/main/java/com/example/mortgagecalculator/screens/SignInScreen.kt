package com.example.mortgagecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mortgagecalculator.screens.House

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



@Composable
fun SignInScreen(onSignInSuccess: () -> Unit) {
    val username = remember { androidx.compose.runtime.mutableStateOf("") }
    val password = remember { androidx.compose.runtime.mutableStateOf("") }


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.sign_on),
                contentDescription = null, // decorative image
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()

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

                Text(
                    text = "Sign In",
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onSignInSuccess,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Text("Log In")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Forgot Password Text
                Text(
                    text = "Forgot your password?",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // No Account Text
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "No account?",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Create one",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            }
        }}




@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(onSignInSuccess = {})
}


