package com.project.toko.detailScreen.ui.detailScreen.mainPage.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.toko.core.ui.theme.evolventaBoldFamily
import com.project.toko.detailScreen.data.model.detailModel.DetailData

@Composable
fun ShowBackground(detailData: com.project.toko.detailScreen.data.model.detailModel.DetailData?, modifier: Modifier) {
    if (detailData?.background?.isNotEmpty() == true) {

        Column(modifier = modifier.padding(start = 20.dp, end = 20.dp)) {

            Text(
                text = "Background",
                color = MaterialTheme.colorScheme.onPrimary,
                lineHeight = 16.sp, fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = evolventaBoldFamily
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = detailData.background,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}