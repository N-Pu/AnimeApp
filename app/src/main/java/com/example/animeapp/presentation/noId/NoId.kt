package com.example.animeapp.presentation.noId

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun NoId(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

        Text(
            text = "No ID MAN",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,

        )

    }


}