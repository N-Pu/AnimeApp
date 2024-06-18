package com.project.toko.detailScreen.ui.detailScreen.sideContent.staffList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.project.toko.R
import com.project.toko.core.ui.navigation.Screen
import com.project.toko.core.ui.theme.evolventaBoldFamily
import com.project.toko.detailScreen.data.model.staffModel.StaffData
import java.lang.Integer.min

@Composable
fun DisplayStaff(
    staffList: List<com.project.toko.detailScreen.data.model.staffModel.StaffData>,
    onNavigateToDetailOnStaff: (String) -> Unit,
    onNavigateToWholeOnStaff: (String) -> Unit,
    modifier: Modifier
) {
    if (staffList.isNotEmpty()) {
        ListEditor(
            listData = staffList,
            onNavigateToDetailStaff = onNavigateToDetailOnStaff,
            onNavigateToWholeStaff = onNavigateToWholeOnStaff,
            modifier = modifier
        )
    }
}


@Composable
private fun ListEditor(
    listData: List<com.project.toko.detailScreen.data.model.staffModel.StaffData>,
    onNavigateToDetailStaff: (String) -> Unit,
    onNavigateToWholeStaff: (String) -> Unit,
    modifier: Modifier
) {
    val minListSize = min(12, listData.size)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp, end = 20.dp, start = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Staff",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = evolventaBoldFamily
            )
        }
        Column {
            Text(
                text = " $minListSize>",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = evolventaBoldFamily
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listData.take(12).forEach { data ->
            val personPainter = rememberAsyncImagePainter(model = data.person.images.jpg.image_url)

            Spacer(modifier = Modifier.size(20.dp))

            StaffComponentsCard(
                data = data,
                personPainter = personPainter,
                onNavigateToDetailStaff = onNavigateToDetailStaff,
                modifier = modifier
            )
        }
        Column(modifier = modifier.width(20.dp)) {
            // Пустая колонка для выравнивания
        }
        Column(
            modifier
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(5.dp))
                .width(160.dp)
                .height(160.dp)
                .background(MaterialTheme.colorScheme.onTertiaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .clickable {
                        onNavigateToWholeStaff(Screen.DetailOnWholeStaff.route)
                    }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.vector),
                    contentDescription = "More staff",
                    modifier = modifier.fillMaxSize(0.7f),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )

            }

            Text(
                text = "More Staff",
                textAlign = TextAlign.Left,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier,
                fontFamily = evolventaBoldFamily
            )
        }
        Column(modifier = modifier.width(20.dp)) {
            // Пустая колонка для выравнивания
        }
    }
}

@Composable
private fun StaffComponentsCard(
    modifier: Modifier,
    data: com.project.toko.detailScreen.data.model.staffModel.StaffData,
    personPainter: AsyncImagePainter,
    onNavigateToDetailStaff: (String) -> Unit
) {
    val positions = data.positions.joinToString(separator = ", ")
    Row(
        modifier = modifier
            .width(320.dp)
            .height(160.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onTertiaryContainer),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .padding(start = 22.dp, top = 22.dp)
                .fillMaxHeight(1f)
        ) {
            Image(
                painter = personPainter,
                contentDescription = "Staff member: ${data.person.name}",
                modifier = modifier
                    .clip(RoundedCornerShape(5.dp))
                    .width(70.dp)
                    .height(107.dp)
                    .clickable {
                        onNavigateToDetailStaff("detail_on_staff/${data.person.id}")
                    },
                contentScale = ContentScale.FillBounds
            )

        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(start = 22.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(top = 24.dp)
            ) {
                Text(
                    text = data.person.name,
                    modifier = modifier,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = evolventaBoldFamily
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f)
                    .padding(end = 22.dp)
            ) {

                Text(
                    text = positions,
                    modifier = modifier,
                    minLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 11.sp,
                    lineHeight = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )


            }
        }

    }
}