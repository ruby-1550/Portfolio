package com.example.mortgagecalculator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mortgagecalculator.R
import com.example.mortgagecalculator.SignInScreen


@Composable
fun HomeDetailsScreen(houses: List<House>, onSelectHouse: (House) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.houses_background),
            contentDescription = null, // decorative image
            contentScale = ContentScale.Crop,


            )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 4.dp) // Add bottom padding to move up from the bottom
                .padding(top = 50.dp)
        ) {

        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 45.sp),
            modifier = Modifier
                .align(Alignment.TopCenter) // Centered on top


        )}
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 4.dp) // Add bottom padding to move up from the bottom
                .padding(top = 170.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(houses) { house ->
                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onSelectHouse(house) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.house),
                                contentDescription = null, // decorative image
                                contentScale = ContentScale.Crop,


                                )
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = house.price,
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = house.description,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }}}

@Preview(showBackground = true)
@Composable
fun HomeDetailsScreenPreview() {
    // Create some mock data
    val mockHouses = listOf(
        House(price = "$300,000", description = "3 bed, 2 bath, 1,800 sq ft"),
        House(price = "$450,000", description = "4 bed, 3 bath, 2,400 sq ft"),
        House(price = "$600,000", description = "5 bed, 4 bath, 3,000 sq ft")
    )

    // Call the HomeDetailsScreen composable with mock data and a dummy onSelectHouse implementation
    HomeDetailsScreen(
        houses = mockHouses,
        onSelectHouse = { house ->
            // Handle house selection in preview (e.g., logging or displaying a toast)
            println("Selected house: $house")
        }
    )
}


