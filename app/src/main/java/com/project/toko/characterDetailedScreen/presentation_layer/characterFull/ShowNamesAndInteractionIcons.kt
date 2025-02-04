package com.project.toko.characterDetailedScreen.presentation_layer.characterFull

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.project.toko.R
import com.project.toko.characterDetailedScreen.dao.CharacterItem
import com.project.toko.characterDetailedScreen.model.characterFullModel.Data
import com.project.toko.characterDetailedScreen.viewModel.CharacterFullByIdViewModel
import com.project.toko.core.presentation_layer.theme.evolventaBoldFamily
import com.project.toko.core.utils.share.shareLink
import com.project.toko.daoScreen.daoViewModel.DaoViewModel
import kotlinx.coroutines.launch

@Composable
fun ShowNamesAndInteractionIcons(
    data: Data,
    modifier: Modifier,
    imageLoader: ImageLoader,
    daoViewModel: DaoViewModel,
    characterViewModel: CharacterFullByIdViewModel
) {

    val isCharacterInDao by daoViewModel.isCharacterInDao(data.mal_id)
        .collectAsStateWithLifecycle(
            initialValue = false
        )
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Text(
            text = data.name,
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            minLines = 1,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = evolventaBoldFamily
        )
        Spacer(modifier = modifier.size(2.dp))

        if (!data.name_kanji.isNullOrEmpty()) {
            Text(
                text = "(" + data.name_kanji + ")",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = modifier.fillMaxWidth(),
                minLines = 1,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 60.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = if (isCharacterInDao) R.drawable.favorite_touched else R.drawable.favorite_untouched,
                        imageLoader = imageLoader
                    ), contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                        .clickable {
                            characterViewModel.viewModelScope.launch {
                                if (isCharacterInDao) {
                                    daoViewModel.removeCharacterFromDataBase(
                                        CharacterItem(
                                            id = data.mal_id,
                                            name = data.name,
                                            anime = data.anime[0].anime.title,
                                            image = data.images.jpg.image_url
                                        )
                                    )
                                } else {
                                    daoViewModel.addCharacter(
                                        CharacterItem(
                                            id = data.mal_id,
                                            name = data.name,
                                            anime = data.anime[0].anime.title,
                                            image = data.images.jpg.image_url
                                        )

                                    )
                                }
                            }
                        },
                    colorFilter = if (isCharacterInDao) null else ColorFilter.tint(
                        MaterialTheme.colorScheme.onError
                    )
                )
                Image(
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onError),
                    painter = rememberAsyncImagePainter(
                        model = R.drawable.links,
                        imageLoader = imageLoader,
                    ), contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                        .clickable {
                            context.shareLink(data.url)
                        }
                )
            }
        }
    }

}