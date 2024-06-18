package com.project.toko.detailScreen.ui.detailScreen.sideContent.staffList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.project.toko.core.ui.theme.BackArrowCastColor
import com.project.toko.core.ui.theme.BackArrowSecondCastColor
import com.project.toko.core.ui.theme.DarkBackArrowCastColor
import com.project.toko.core.ui.theme.DarkBackArrowSecondCastColor
import com.project.toko.core.ui.theme.evolventaBoldFamily
import com.project.toko.detailScreen.ui.viewModel.DetailScreenViewModel
import com.project.toko.detailScreen.data.model.staffModel.Person


@Composable
fun ShowWholeStaff(
    onNavigateToDetailOnStaff: (String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: DetailScreenViewModel,
    modifier: Modifier = Modifier,
    isInDarkTheme: () -> Boolean
) {

    val staffState by viewModel.staffList.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 265.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        item { Spacer(modifier = modifier.height(70.dp)) }
        items(staffState) { data ->
            SingleStaffMember(
                person = data.person,
                positions = data.positions,
                onNavigateToDetailOnStaff = onNavigateToDetailOnStaff,
                modifier = modifier
            )
        }
        item { Spacer(modifier = modifier.height(50.dp)) }

    }
    BackArrow(
        modifier, onNavigateBack, viewModel.animeDetails.value?.mal_id ?: 0, isInDarkTheme
    )
}

@Composable
private fun BackArrow(
    modifier: Modifier,
    onNavigateBack: () -> Unit,
    detailScreenMalId: Int,
    isInDarkTheme: () -> Boolean
) {
    val backArrowFirstColor =
        if (isInDarkTheme()) DarkBackArrowCastColor else BackArrowCastColor
    val backArrowSecondColor =
        if (isInDarkTheme()) DarkBackArrowSecondCastColor else BackArrowSecondCastColor
    Column {
        Spacer(modifier = modifier.height(40.dp))
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(backArrowFirstColor)
        ) {
            Text(
                text = "   <    Staff                         ",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                textDecoration = TextDecoration.Underline,
                modifier = modifier.clickable {
//                    navController.navigate("detail_screen/$detailScreenMalId") {
//                        launchSingleTop = true
//                        popUpTo(route = Screen.DetailOnWholeStaff.route) {
//                            inclusive = true
//                        }
//                    }
                    onNavigateBack()
                },
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = evolventaBoldFamily
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.02f)
                .background(backArrowSecondColor)
        )

        Spacer(modifier = modifier.height(20.dp))
    }
}


@Composable
private fun SingleStaffMember(
    person: com.project.toko.detailScreen.data.model.staffModel.Person,
    positions: List<String>,
    onNavigateToDetailOnStaff: (String) -> Unit,
    modifier: Modifier
) {

    var isVisible by remember {
        mutableStateOf(false)
    }
    val painter = rememberAsyncImagePainter(model = person.images.jpg.image_url)
    val stringPositions = positions.joinToString(separator = ", ")
    AnimatedVisibility(
        visible = isVisible, enter = slideInHorizontally(
            animationSpec = TweenSpec(durationMillis = 500)
        ), exit = slideOutHorizontally(
            animationSpec = TweenSpec(durationMillis = 500)
        )
    ) {

        Box(modifier = modifier.clickable {
            onNavigateToDetailOnStaff("detail_on_staff/${person.id}")
        }) {
            Row {
                Column(modifier = modifier.padding(vertical = 2.dp, horizontal = 10.dp)) {
                    Image(
                        painter = painter,
                        contentDescription = person.name,
                        modifier = modifier
                            .size(100.dp, 150.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.FillBounds
                    )

                }
                Column {
                    Text(
                        text = person.name,
                        modifier = modifier,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = evolventaBoldFamily
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth(0.7f)
                            .height(60.dp)
                    ) {
                        Text(
                            text = stringPositions,
                            modifier = Modifier,
                            minLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                }
            }

        }

    }

    LaunchedEffect(person) {
        isVisible = true
    }


}


