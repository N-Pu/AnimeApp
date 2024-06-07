package com.project.toko.core.presentation_layer.navigation


import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.ImageLoader
import com.project.toko.detailScreen.viewModel.DetailScreenViewModel
import com.project.toko.detailScreen.presentation_layer.detailScreen.mainPage.ActivateDetailScreen
import com.project.toko.homeScreen.presentation_layer.homeScreen.MainScreen
import com.project.toko.noId.presentation_layer.noId.NoId
import com.project.toko.detailScreen.presentation_layer.detailScreen.sideContent.castList.ShowWholeCast
import com.project.toko.detailScreen.presentation_layer.detailScreen.sideContent.staffList.ShowWholeStaff
import com.project.toko.characterDetailedScreen.presentation_layer.characterFull.DisplayCharacterFromId
import com.project.toko.daoScreen.presentation_layer.DaoScreen
import com.project.toko.personDetailedScreen.presentation_layer.staffMemberFull.DisplayPersonFullScreen
import com.project.toko.randomAnimeScreen.presentation_layer.randomAnimeScreen.ShowRandomAnime

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    isInDarkTheme: () -> Boolean,
    drawerState: DrawerState,
    svgImageLoader: ImageLoader
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) {
            MainScreen(
                navController = navController,
                modifier = modifier,
                isInDarkTheme = isInDarkTheme,
                drawerState = drawerState,
                svgImageLoader = svgImageLoader
            )
        }
        composable(
            route = Screen.Detail.route, arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            ActivateDetailScreen(
                navController = navController,
                id = id,
                modifier = modifier,
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }

        composable(route = Screen.Nothing.route) {
            NoId()
        }
        composable(route = Screen.Favorites.route) {
            DaoScreen(
                navController = navController,
                modifier = modifier,
                isInDarkTheme = isInDarkTheme,
                drawerState = drawerState,
                svgImageLoader = svgImageLoader
            )
        }
        composable(route = Screen.RandomAnimeOrManga.route) {
            ShowRandomAnime(
                navController = navController,
                modifier = modifier,
            )
        }
        composable(route = Screen.DetailOnWholeCast.route) {
            val detailScreenViewModel : DetailScreenViewModel = hiltViewModel()
            ShowWholeCast(
                navController,
                detailScreenViewModel,
                modifier,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.DetailOnWholeStaff.route) {
            val detailScreenViewModel : DetailScreenViewModel = hiltViewModel()
            ShowWholeStaff(
                navController,
                detailScreenViewModel,
                modifier = modifier,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(
            route = Screen.CharacterDetail.route, arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayCharacterFromId(
                id = id,
                navController = navController,
                modifier = modifier,
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
        composable(
            route = Screen.StaffDetail.route, arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayPersonFullScreen(
                id = id,
                navController = navController,
                modifier = modifier,
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }

    }
}

//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
//    val navGraphRoute = destination.parent?.route ?: return viewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//    return viewModel(parentEntry)
//}