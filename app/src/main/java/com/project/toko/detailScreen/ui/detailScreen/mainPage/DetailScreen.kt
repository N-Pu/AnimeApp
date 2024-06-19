package com.project.toko.detailScreen.ui.detailScreen.mainPage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.project.toko.core.ui.animations.LoadingAnimation
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.AddToFavorites
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.DisplayJapAndEnglishTitles
import com.project.toko.detailScreen.ui.detailScreen.sideContent.castList.DisplayCast
import com.project.toko.detailScreen.ui.detailScreen.mainPage.customVisuals.DisplayCustomGenreBoxes
import com.project.toko.detailScreen.ui.detailScreen.sideContent.staffList.DisplayStaff
import com.project.toko.detailScreen.ui.viewModel.DetailScreenViewModel
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.DisplayPicture
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.DisplayTitle
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ExpandableRelated
import com.project.toko.core.ui.expandableText.ExpandableText
import com.project.toko.core.ui.pullToRefpresh.PullToRefreshLayout
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.FavoritesLine
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.MembersLine
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.PopularityLine
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.RankedLine
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.Recommendations
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ScoreByNumber
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ScoreLabel
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ScoreNumber
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ShowBackground
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ShowMoreInformation
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.ShowPictureAlbum
import com.project.toko.detailScreen.ui.detailScreen.mainPage.custom.YearTypeEpisodesTimeStatusStudio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivateDetailScreen(
    onNavigateToDetailOnCharacter: (Int) -> Unit,
    onNavigateToDetailOnStaff: (Int) -> Unit,
    onNavigateToWholeOnStaff: () -> Unit,
    onNavigateToDetailScreen: (Int) -> Unit,
    onNavigateToDetailOnWholeCast: (Int) -> Unit,
    id: Int,
    modifier: Modifier = Modifier,
    isInDarkTheme: () -> Boolean,
    svgImageLoader: ImageLoader
) {

    val viewModel: DetailScreenViewModel = hiltViewModel()

    val detailData by
    viewModel.animeDetails.collectAsStateWithLifecycle()

    val castData by viewModel.castList.collectAsStateWithLifecycle()
    val staffData by viewModel.staffList.collectAsStateWithLifecycle()
    val recommendationsData by viewModel.recommendationList.collectAsStateWithLifecycle()
    val scrollState = viewModel.scrollState
    val picturesData by viewModel.picturesData.collectAsStateWithLifecycle()
    val isAlbumDialogShown = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.isLoading.value)


    LaunchedEffect(key1 = id) {
        withContext(Dispatchers.IO) {
            viewModel.loadAllInfo(id, context)
        }
    }


    val model = ImageRequest.Builder(LocalContext.current)
        .data(detailData?.images?.jpg?.large_image_url)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()

    val painter =
        rememberAsyncImagePainter(
            model
        )

    if (
        viewModel.isLoading.value.not()
//        &&
//            detailData != null
    ) {
        PullToRefreshLayout(
            composable = {
                Column(
                    modifier = modifier
                        .verticalScroll(scrollState)
                        .background(MaterialTheme.colorScheme.errorContainer),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    DisplayPicture(
                        painter = painter,
                        modifier = modifier
                            .fillMaxSize()
                            .combinedClickable(onClick = {}, onLongClick = {
                                isAlbumDialogShown.value = true
                            })
                    )

                    ShowPictureAlbum(
                        isDialogShown = isAlbumDialogShown,
                        picturesData = picturesData,
                        modifier = modifier
                    )

                    DisplayTitle(title = detailData?.title ?: "No title name", modifier)
                    Spacer(modifier = modifier.height(20.dp))
                    DisplayJapAndEnglishTitles(detailData = detailData, modifier = modifier)
                    Spacer(modifier = modifier.height(20.dp))
                    YearTypeEpisodesTimeStatusStudio(data = detailData, modifier = modifier)

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.size(150.dp)
                        ) {

                            ScoreLabel(modifier = modifier)
                            ScoreNumber(modifier = modifier, score = detailData?.score ?: 0.0f)
                            ScoreByNumber(
                                scoreBy = detailData?.scored_by ?: 0.0f,
                                modifier = modifier
                            )

                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.size(height = 150.dp, width = 150.dp)
                        ) {

                            RankedLine(
                                rank = detailData?.rank ?: 0,
                                modifier = modifier
                            )
                            PopularityLine(
                                popularity = detailData?.popularity ?: 0,
                                modifier = modifier
                            )
                            MembersLine(
                                members = detailData?.members ?: 0,
                                modifier = modifier
                            )

                            FavoritesLine(
                                favorites = detailData?.favorites ?: 0,
                                modifier = modifier
                            )
                        }
                    }
                    DisplayCustomGenreBoxes(
                        genres = detailData?.genres ?: listOf(),
                        modifier = modifier
                    )
                    AddToFavorites(
                        modifier,
                        isInDarkTheme,
                        svgImageLoader = svgImageLoader
                    )
                    ExpandableText(
                        text = detailData?.synopsis,
                        title = "Synopsis",
                        modifier = modifier
                    )


//            FullScreenYoutubeActivity().YoutubePlayerSecond(
//                detailData?.trailer?.youtube_id ?: "",
//                LocalLifecycleOwner.current,
//                modifier
//            )

//            YoutubePlayer(
//                detailData?.trailer?.youtube_id ?: "",
//                LocalLifecycleOwner.current,
//                modifier
//            )

                    ShowMoreInformation(modifier = modifier, detailData = detailData)
                    ShowBackground(detailData = detailData, modifier = modifier)
                    DisplayCast(
                        castList = castData,
                        onNavigateToDetailOnCharacter = onNavigateToDetailOnCharacter,
                        onNavigateToDetailOnStaff = onNavigateToDetailOnStaff,
                        onNavigateToWholeOnCast = onNavigateToDetailOnWholeCast,
                        modifier = modifier,
                        detailMalId = viewModel.loadedId.intValue
                    )
                    DisplayStaff(
                        staffList = staffData,
                   onNavigateToDetailOnStaff = onNavigateToDetailOnStaff,
                        onNavigateToWholeOnStaff = onNavigateToWholeOnStaff,
                        modifier = modifier
                    )
//            ShowStudios(detailData, navController)
                    ExpandableRelated(
                        relations = detailData?.relations,
                        modifier = modifier,
                        onNavigateToDetailScreen = onNavigateToDetailScreen
                    )
                    Recommendations(
                        recommendationsData,
                        onNavigateToDetailScreen = onNavigateToDetailScreen,
                        modifier,
                        isInDarkTheme
                    )
                    Spacer(modifier = modifier.height(20.dp))
                }
            },
            onLoad = {
                viewModel.viewModelScope.launch {
                    viewModel.refreshAndLoadAllInfo(id, context)
                }
            },
            swipeRefreshState = swipeRefreshState
        )

    } else {
        LoadingAnimation()
    }


}
