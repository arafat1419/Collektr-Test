package com.arafat1419.collektr.presentation.ui.features.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            modifier = modifier,
            navController = navController,
            navigationItem = NavigationItem.Profile
        )

        Card(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "https://media.licdn.com/dms/image/v2/C5603AQGl-ghmKeO2aw/profile-displayphoto-shrink_400_400/profile-displayphoto-shrink_400_400/0/1651997098385?e=1733356800&v=beta&t=d8wwRbtd1mnE7T6JTOtIkNnoSZD7sRFouxhEXjzqKIs",
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Mohammad Arafat Maku",
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "arafat1419@gmail.com",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}