package com.example.animeapp.presentation.detailScreen.sideContent.staffMemberFull


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import com.example.animeapp.presentation.animations.LoadingAnimation
import com.example.animeapp.viewModel.StaffFullByIdViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayStaffMemberFromId(
    mal_id: Int, navController: NavController, viewModelProvider: ViewModelProvider
) {


    LaunchedEffect(mal_id) {
        withContext(Dispatchers.IO) {
            viewModelProvider[StaffFullByIdViewModel::class.java].getStaffFromId(mal_id)
//            viewModelProvider[CharacterPicturesViewModel::class.java].getPicturesFromId(mal_id)
        }


    }


    val isSearching by viewModelProvider[StaffFullByIdViewModel::class.java].isSearching.collectAsStateWithLifecycle()
    val staffFullState =
        viewModelProvider[StaffFullByIdViewModel::class.java].staffFull.collectAsStateWithLifecycle()
    val painter =
        rememberAsyncImagePainter(model = staffFullState.value?.images?.jpg?.image_url)


//    val characterPicturesState =
//        viewModelProvider[CharacterPicturesViewModel::class.java].picturesList.collectAsStateWithLifecycle()


    if (isSearching.not()) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


//            if (characterPicturesState.value.isNotEmpty()) {
//            DisplayAlbumHorizontally(
//                painterList = characterPicturesState.value
//            )
//            } else {
            Image(
                painter = painter,
                contentDescription = "Character name: ${staffFullState.value?.name}",
                modifier = Modifier
                    .size(400.dp)
                    .aspectRatio(9 / 11f)
            )
//
//            }


            Spacer(modifier = Modifier.size(28.dp))
//            staffFullState.value?.let { data ->
//                DisplayKanjiAndEnglishName(data = data.)
//
//            }
            staffFullState.value?.let {
                Text(
                    text = it.name,
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
            }
            // writes null in ui if there's no "about" data
            staffFullState.value?.about?.let { about ->
                Text(
                    text = about, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                )
            }

//            Text(text = "Also known as:")
//            characterFullState.value?.nicknames?.forEach {
//                Text(text = it, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
//            }

            staffFullState.value?.anime?.forEach { anime ->
                Text(
                    text = anime.anime.title,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = anime.position,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    } else {
        LoadingAnimation()
    }
}


//}


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun DisplayAlbumHorizontally(painterList: List<Data>) {
//    val pagerState = rememberPagerState(initialPage = painterList.size)
//    HorizontalPager(
//        state = pagerState,
//        modifier = Modifier.size(400.dp),
//        pageCount = painterList.size,
//        reverseLayout = true
//    ) { page ->
//        val painter = rememberAsyncImagePainter(model = painterList[page].jpg.image_url)
//        Log.d("pic", painterList[page].jpg.image_url)
//        Image(
//            painter = painter,
//            contentDescription = null,
//            modifier = Modifier
//                .size(400.dp)
//                .aspectRatio(9 / 11f),
//        )
//    }
//}


