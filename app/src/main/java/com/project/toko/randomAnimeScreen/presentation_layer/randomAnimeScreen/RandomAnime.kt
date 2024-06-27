package com.project.toko.randomAnimeScreen.presentation_layer.randomAnimeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.project.toko.core.domain.util.stateCheck.CheckState
import com.project.toko.daoScreen.data.dao.AnimeItem
import com.project.toko.randomAnimeScreen.presentation_layer.viewModel.RandomAnimeViewModel
import com.project.toko.core.ui.theme.DialogColor
import com.project.toko.core.ui.theme.evolventaBoldFamily
import com.project.toko.daoScreen.ui.daoViewModel.DaoViewModel
import com.project.toko.daoScreen.data.model.AnimeStatus
import com.project.toko.homeScreen.data.model.newAnimeSearchModel.AnimeSearchData
import com.project.toko.randomAnimeScreen.data.model.AnimeRandomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale


@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun ShowRandomAnime(
    onNavigateToDetailScreen: (Int) -> Unit, modifier: Modifier = Modifier
) {
    val randomViewModel: RandomAnimeViewModel = hiltViewModel()
    val daoViewModel: DaoViewModel = hiltViewModel()
    val randomResponseState by randomViewModel.randomResponse.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val swipeState = rememberSwipeableCardState()
    var checkIfCardIsGone by remember { mutableStateOf(false) }
    var currentAnimeItem by remember {
        mutableStateOf(
            AnimeItem(
                id = 0,
                animeName = "N/A",
                animeImage = "",
                score = "",
                scored_by = "",
                category = AnimeStatus.PLANNED.route,
                status = "",
                rating = "",
                secondName = "",
                airedFrom = "",
                type = ""
            )
        )
    }

    Column(
        modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (randomResponseState) {
            is CheckState.Error -> {
                val errorMessage = (randomResponseState as CheckState.Error).message
                ShowError(
                    randomViewModel = randomViewModel,
                    errorMessage = errorMessage
                )
            }

            is CheckState.Loading -> {
                // Handle loading state for regular quotes
                // Render loading indicator or any UI
                CenteredCircularProgressIndicator()
            }

            is CheckState.Success -> {
                // Handle success state for regular quotes
                val quoteData =
                    (randomResponseState as CheckState.Success<AnimeRandomModel?>).data?.data
                // Update currentId with the latest id

                currentAnimeItem = AnimeItem(
                    id = quoteData?.id ?: 0,
                    animeName = quoteData?.title ?: "N/A",
                    animeImage = quoteData?.images?.jpg?.image_url
                        ?: "",
                    score = formatScoredBy(quoteData?.score ?: 0.0f),
                    scored_by = formatScoredBy(
                        quoteData?.scored_by ?: 0.0f
                    ),
                    category = AnimeStatus.PLANNED.route,
                    status = quoteData?.status ?: "",
                    rating = quoteData?.rating ?: "",
                    secondName = quoteData?.title_japanese ?: "",
                    airedFrom = quoteData?.aired?.from ?: "N/A",
                    type = quoteData?.type ?: "N/A"
                )


                // Render UI with quoteData
                AnimeCard(
                    data = quoteData,
                    modifier = modifier
                        .width(340.dp)
                        .height(510.dp)
                        .swipableCard(
                            blockedDirections = listOf(Direction.Down),
                            state = swipeState, onSwiped = { direction ->
                                when (direction) {
                                    Direction.Left -> {
                                        randomViewModel.fetchRandomAnime()
                                    }

                                    Direction.Right -> {
                                        daoViewModel.addToCategory(
                                            currentAnimeItem
                                        )

                                        randomViewModel.fetchRandomAnime()
                                    }

                                    Direction.Up -> {
                                        onNavigateToDetailScreen(
                                            currentAnimeItem.id ?: 0
                                        )  // Use currentId
                                    }

                                    else -> {
                                        checkIfCardIsGone = true
                                    }
                                }
                            },
                            onSwipeCancel = {
                                Toast
                                    .makeText(
                                        context,
                                        "Swipe canceled!",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        ),
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                    context = context
                )

                LaunchedEffect(currentAnimeItem.id) {
                    withContext(Dispatchers.IO) {
                        swipeState.offset.snapTo(Offset(0.0f, 1000.0f))
                        swipeState.offset.animateTo(Offset(0.0f, 0.0f))
                    }
                }

            }
        }

    }
    LaunchedEffect(checkIfCardIsGone) {
        if (checkIfCardIsGone) {
            withContext(Dispatchers.IO) {
                swipeState.offset.animateTo(Offset(0.0f, 0.0f))
                checkIfCardIsGone = false
            }
        }
    }
}


@Composable
fun CenteredCircularProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp) // Set the size of the CircularProgressIndicator
                .align(Alignment.Center) // Align it to the center of the Box
            , color = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
private fun AnimeCard(
    data: AnimeSearchData?,
    modifier: Modifier,
    onNavigateToDetailScreen: (Int) -> Unit,
    context: Context,
) {

    val model = ImageRequest.Builder(context).data(data?.images?.jpg?.large_image_url)
        .size(Size.ORIGINAL).crossfade(true).build()
    val isStudioEmpty = data?.studios.isNullOrEmpty()
    val painter = rememberAsyncImagePainter(model = model)
    val scoreRoundedCornerShape = remember { RoundedCornerShape(bottomEnd = 10.dp) }
    val clickableModifier = Modifier.clickable {
        if (data != null) {
            onNavigateToDetailScreen(data.id)
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
            .shadow(150.dp, shape = CardDefaults.shape, clip = true)
    ) {


        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.095f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(start = 20.dp, end = 20.dp)
            ) {

                Text(
                    text = data?.title ?: "",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    letterSpacing = 3.sp,
                    lineHeight = 1.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = evolventaBoldFamily
                )

            }

            if (data?.title_english != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight(1f)
                ) {

                    Text(
                        text = data.title_english,
                        textAlign = TextAlign.End,
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        letterSpacing = 2.sp,
                        lineHeight = 5.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .size(300.dp, 420.dp)
                .clip(CardDefaults.shape),
        ) {
            Box {
                Image(
                    painter = painter,
                    contentDescription = "Anime ${data?.title}",
                    modifier = Modifier
                        .then(clickableModifier)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )


                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                scoreColor(data?.score ?: 0.0f), scoreRoundedCornerShape
                            )
                            .size(75.dp), contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = if (data?.score.toString() == "0.0") "N/A" else data?.score.toString(),
                            color = Color.White,
                            fontSize = 25.sp,
                            overflow = TextOverflow.Ellipsis,
                        )

                    }
                }


                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
//                            .shadow(
//                                elevation = 10.dp,
//                                spotColor = Color(0f, 0f, 0f, 1f),
//                                ambientColor = Color(0f, 0f, 0f, 1f)
//                            )
                    ) {


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(
                                    rememberScrollState()
                                ), horizontalArrangement = Arrangement.Center
                        ) {
                            val numbOfGenres = data?.genres?.count()

                            if (numbOfGenres != null) {
                                if (numbOfGenres <= 3) {
                                    DisplayCustomGenres(
                                        genres = data.genres, modifier = Modifier
                                    )
                                }

                                if (numbOfGenres > 3) {
                                    DisplayCustomGenres(
                                        genres = data.genres.take(3), modifier = Modifier
                                    )
                                }

                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "   Status: ", fontSize = 12.sp, color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                            )

                            Text(
                                text = data?.status ?: "",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                overflow = TextOverflow.Ellipsis,
                            )

                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "   Rating: ", fontSize = 12.sp, color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                            )

                            Text(
                                text = data?.rating ?: "N/A",
                                fontSize = 12.sp,
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                            )

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {

                            Text(
                                text = "   Episodes: " + data?.episodes,
                                fontSize = 12.sp,
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                            )

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                }


            }
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (data?.year != 0 && data?.season != null) {

                Text(
                    data.season + " " + data.year.toString(),
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(" | ", color = DialogColor)
            }
            Text(data?.type ?: "N/A", color = MaterialTheme.colorScheme.secondary)
            if (!isStudioEmpty) {
                Text(" | ", color = DialogColor)

                Text(
                    data?.studios?.component1()?.name ?: "N/A",
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }


    }
}

@Composable
private fun ColoredBox(
    text: String, modifier: Modifier
) {

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(15.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(4.dp),
            fontFamily = evolventaBoldFamily
        )
    }
}

@Composable
private fun DisplayCustomGenres(
    genres: List<com.project.toko.homeScreen.data.model.newAnimeSearchModel.Genre>,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        genres.forEachIndexed { index, genre ->
            if (index != 0) {
                Spacer(modifier = modifier.width(8.dp))
            }
            ColoredBox(
                text = genre.name, modifier
            )
        }
    }
}

private fun formatScoredBy(float: Float): String {
    return if (float == 0f) {
        "N/A"
    } else {
        val formattedString = String.format(Locale.US, "%.1f", float)
        if (formattedString.endsWith(".0")) {
            formattedString.substring(0, formattedString.length - 2)
        } else {
            formattedString.replace(",", ".")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowError(randomViewModel: RandomAnimeViewModel, errorMessage: String) {
    Box(modifier = Modifier
        .clip(CardDefaults.shape)
        .background(MaterialTheme.colorScheme.secondary)
        .combinedClickable(onDoubleClick = {
            randomViewModel.fetchRandomAnime()
        }) {}
        .padding(20.dp)) {
        Text(
            text = errorMessage,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = evolventaBoldFamily
        )
    }
}