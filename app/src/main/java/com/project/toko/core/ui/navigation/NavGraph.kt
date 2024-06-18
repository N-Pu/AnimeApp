package com.project.toko.core.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import coil.ImageLoader
import com.project.toko.detailScreen.ui.viewModel.DetailScreenViewModel
import com.project.toko.detailScreen.ui.detailScreen.mainPage.ActivateDetailScreen
import com.project.toko.homeScreen.ui.homeScreen.MainScreen
import com.project.toko.detailScreen.ui.detailScreen.sideContent.castList.ShowWholeCast
import com.project.toko.detailScreen.ui.detailScreen.sideContent.staffList.ShowWholeStaff
import com.project.toko.characterDetailedScreen.ui.characterFull.DisplayCharacterFromId
import com.project.toko.daoScreen.ui.screen.DaoScreen
import com.project.toko.personDetailedScreen.ui.staffMemberFull.DisplayPersonFullScreen
import com.project.toko.randomAnimeScreen.presentation_layer.randomAnimeScreen.ShowRandomAnime

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    isInDarkTheme: () -> Boolean,
    drawerState: DrawerState,
    svgImageLoader: ImageLoader
) {
    NavHost(navController = navController, startDestination = Screen.HomeSubGraph.route) {

        homeSubGraph(navController, drawerState, svgImageLoader, isInDarkTheme)
        daoSubGraph(navController, drawerState, svgImageLoader, isInDarkTheme)
        randomSubGraph(navController, drawerState, svgImageLoader, isInDarkTheme)

    }
}

private fun NavGraphBuilder.homeSubGraph(
    navController: NavController, drawerState: DrawerState,
    svgImageLoader: ImageLoader, isInDarkTheme: () -> Boolean,
) {
    // Main Graph
    navigation(startDestination = Screen.Home.route, route = Screen.HomeSubGraph.route) {
        composable(route = Screen.Home.route) {
            MainScreen(
                onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                isInDarkTheme = isInDarkTheme,
                drawerState = drawerState,
                svgImageLoader = svgImageLoader
            )
        }
        composable(route = Screen.Detail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            ActivateDetailScreen(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                }, onNavigateToWholeOnStaff = { wholeCastId ->
                    navController.navigate(wholeCastId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                id = id,
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
//            composable(route = Screen.Nothing.route) {
//                NoId()
//            }
        composable(route = Screen.DetailOnWholeCast.route) {
            val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
            ShowWholeCast(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                },
                onNavigateBack = { navController.navigateUp() },
                detailScreenViewModel,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.DetailOnWholeStaff.route) {
            val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
            ShowWholeStaff(
                onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                },
                onNavigateBack = { navController.navigateUp() },
                detailScreenViewModel,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.CharacterDetail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayCharacterFromId(
                id = id,
                onNavigateToStaff = { staffId ->
                    navController.navigate(staffId)
                },
                onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
        composable(route = Screen.StaffDetail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayPersonFullScreen(
                id = id,
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = { navController.navigateUp() },
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
    }
}

private fun NavGraphBuilder.daoSubGraph(
    navController: NavController, drawerState: DrawerState,
    svgImageLoader: ImageLoader, isInDarkTheme: () -> Boolean,
): Unit {
    // Dao Graph
    navigation(startDestination = Screen.Favorites.route, route = Screen.DaoSubGraph.route) {
        composable(route = Screen.Favorites.route) {
            DaoScreen(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                isInDarkTheme = isInDarkTheme,
                drawerState = drawerState,
                svgImageLoader = svgImageLoader
            )
        }
        composable(route = Screen.Detail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            ActivateDetailScreen(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                }, onNavigateToWholeOnStaff = { wholeCastId ->
                    navController.navigate(wholeCastId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                id = id,
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
//            composable(route = Screen.Nothing.route) {
//                NoId()
//            }
        composable(route = Screen.DetailOnWholeCast.route) {
            val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
            ShowWholeCast(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                },
                onNavigateBack = { navController.navigateUp() },
                detailScreenViewModel,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.DetailOnWholeStaff.route) {
            val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
            ShowWholeStaff(
                onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                },
                onNavigateBack = { navController.navigateUp() },
                detailScreenViewModel,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.CharacterDetail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayCharacterFromId(
                id = id,
                onNavigateToStaff = { staffId ->
                    navController.navigate(staffId)
                },
                onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
        composable(route = Screen.StaffDetail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayPersonFullScreen(
                id = id,
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = { navController.navigateUp() },
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
    }
}

fun NavGraphBuilder.randomSubGraph(
    navController: NavController, drawerState: DrawerState,
    svgImageLoader: ImageLoader, isInDarkTheme: () -> Boolean,
) {
    // Random Anime Graph
    navigation(
        startDestination = Screen.RandomAnimeOrManga.route,
        route = Screen.RandomSubGraph.route
    ) {
        composable(route = Screen.RandomAnimeOrManga.route) {
            ShowRandomAnime(
                onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(route = Screen.Detail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            ActivateDetailScreen(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                }, onNavigateToWholeOnStaff = { wholeCastId ->
                    navController.navigate(wholeCastId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                id = id,

                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
//            composable(route = Screen.Nothing.route) {
//                NoId()
//            }
        composable(route = Screen.DetailOnWholeCast.route) {
            val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
            ShowWholeCast(
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                },
                onNavigateBack = { navController.navigateUp() },
                detailScreenViewModel,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.DetailOnWholeStaff.route) {
            val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
            ShowWholeStaff(
                onNavigateToDetailOnStaff = { personId ->
                    navController.navigate(personId)
                },
                onNavigateBack = { navController.navigateUp() },
                detailScreenViewModel,
                isInDarkTheme = isInDarkTheme
            )
        }
        composable(route = Screen.CharacterDetail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayCharacterFromId(
                id = id,
                onNavigateToStaff = { staffId ->
                    navController.navigate(staffId)
                },
                onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
        composable(route = Screen.StaffDetail.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            DisplayPersonFullScreen(
                id = id,
                onNavigateToDetailOnCharacter = { characterId ->
                    navController.navigate(characterId)
                }, onNavigateToDetailScreen = { detailScreenId ->
                    navController.navigate(detailScreenId) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = { navController.navigateUp() },
                isInDarkTheme = isInDarkTheme,
                svgImageLoader = svgImageLoader
            )
        }
    }
}
