package com.project.toko.characterDetailedScreen.presentation_layer.characterFull

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.project.toko.characterDetailedScreen.viewModel.CharacterFullByIdViewModel
import com.project.toko.core.presentation_layer.animations.LoadingAnimation
import com.project.toko.core.presentation_layer.expandableText.ExpandableText
import com.project.toko.core.presentation_layer.pullToRefpresh.PullToRefreshLayout
import com.project.toko.core.presentation_layer.theme.BackArrowCastColor
import com.project.toko.core.presentation_layer.theme.BackArrowSecondCastColor
import com.project.toko.core.presentation_layer.theme.DarkBackArrowCastColor
import com.project.toko.core.presentation_layer.theme.DarkBackArrowSecondCastColor
import com.project.toko.core.presentation_layer.theme.evolventaBoldFamily
import com.project.toko.daoScreen.daoViewModel.DaoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun DisplayCharacterFromId(
    id: Int,
    navController: NavController,

    modifier: Modifier, isInDarkTheme: () -> Boolean, svgImageLoader: ImageLoader
) {
    val characterViewModel :CharacterFullByIdViewModel = hiltViewModel()
    val daoViewModel :DaoViewModel = hiltViewModel()
    val context = LocalContext.current
    LaunchedEffect(id) {
        withContext(Dispatchers.IO) {
            characterViewModel.loadAllInfo(id, context)
        }
    }
    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = characterViewModel.isLoading.value)
    val characterFullState by
    characterViewModel.characterFull.collectAsStateWithLifecycle()
    val characterPicturesState by
    characterViewModel.picturesList.collectAsStateWithLifecycle()
    val painter = rememberAsyncImagePainter(model = characterFullState?.images?.jpg?.image_url)

    val isDialogShown = remember { mutableStateOf(false) }
    if (characterViewModel.isLoading.value.not() && characterFullState != null) {

        PullToRefreshLayout(
            composable = {
                Column(
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(modifier = modifier.height(100.dp)) {}
                    Row(
                        modifier = modifier
                            .height(250.dp)
                    ) {

                        ShowCharacterPicture(
                            painter = painter,
                            modifier = modifier,
                            isDialogShown = isDialogShown
                        )
                        ShowNamesAndInteractionIcons(
                            data = characterFullState!!,
                            modifier = modifier,
                            imageLoader = svgImageLoader,
                            daoViewModel = daoViewModel,
                            characterViewModel = characterViewModel
                        )
                        ShowCharacterPictureAlbum(
                            isDialogShown = isDialogShown,
                            picturesData = characterPicturesState,
                            modifier = modifier
                        )
                    }
                    Row {
                        characterFullState?.about?.let { about ->
                            ExpandableText(
                                text = about,
                                title = "More Information",
                                modifier = modifier
                            )
                        }
                    }
                    if (
                        !characterFullState?.voices.isNullOrEmpty()
                    ) {
                        ShowVoiceActors(
                            modifier = modifier,
                            actors = characterFullState!!.voices,
                            navController = navController
                        )

                    }

                    if (
                        !characterFullState?.anime.isNullOrEmpty()
                    ) {
                        ShowAnimeRelated(
                            modifier = modifier,
                            animes = characterFullState?.anime!!,
                            navController = navController
                        )

                    }
                }
                BackArrow(modifier, navController, isInDarkTheme)

            },
            onLoad = {
                characterViewModel.viewModelScope.launch {
                    characterViewModel.loadAllInfo(id, context)
                }
            },
            swipeRefreshState = swipeRefreshState
        )

    } else LoadingAnimation()
}


@Composable
private fun BackArrow(
    modifier: Modifier,
    navController: NavController,
    isInDarkTheme: () -> Boolean
) {
    val backArrowFirstColor = if (isInDarkTheme()) DarkBackArrowCastColor else BackArrowCastColor
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
                text = "   <    Character                    ",
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                textDecoration = TextDecoration.Underline,
                modifier = modifier.clickable {
                    navController.popBackStack()
                },
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = evolventaBoldFamily,
                fontWeight = FontWeight.W900
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

