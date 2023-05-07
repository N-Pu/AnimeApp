package com.example.animeapp.presentation.appConstraction


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animeapp.presentation.navigation.CharacterDetail
import com.example.animeapp.presentation.navigation.Nothing
import com.example.animeapp.presentation.navigation.Screen
import com.example.animeapp.presentation.navigation.SetupNavGraph
import com.example.animeapp.presentation.navigation.StaffDetail
import com.example.animeapp.viewModel.IdViewModel

import androidx.compose.material3.BottomAppBar as BottomAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokoAppActivator(
    navController: NavHostController,
    viewModelProvider: ViewModelProvider,
//    savedAnimeViewModel: SavedAnimeViewModel,

) {


    var showButton by remember { mutableStateOf(false) }

    //check if destination matches needed route -> show button
    navController.addOnDestinationChangedListener { _, destination, _ ->
        showButton = when (destination.route) {
            Screen.Detail.route, CharacterDetail.value, StaffDetail.value -> true
            else -> false
        }
    }


    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController,
            idViewModel = viewModelProvider[IdViewModel::class.java]
        )
    }, floatingActionButton = {
//        if (showButton) {
        MyFloatingButton(navController = navController, showButton = showButton)
//        }

    }, floatingActionButtonPosition = FabPosition.Center, content = { padding ->
        padding.calculateTopPadding()
        SetupNavGraph(
            navController, viewModelProvider
        )
    })


    LaunchedEffect(showButton) {
        showButton = true
    }

}


@Composable
fun MyFloatingButton(navController: NavController, showButton: Boolean) {
    AnimatedVisibility(
        visible = showButton, enter = slideInVertically(
            initialOffsetY = { -it }, // отрицательное значение для появления сверху вниз
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(durationMillis = 500)), exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        FloatingActionButton(
            onClick = { }, containerColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Localized description")
        }
    }
}


@Composable
fun BottomNavigationBar(
    navController: NavController, idViewModel: IdViewModel
) {

    val items = listOf(
        Screen.Home, Screen.Detail, Screen.News, Screen.Favorites
    )

//    val id by idViewModel.mal_id.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar {
        NavigationBarItem(icon = {
            Icon(
                imageVector = items[0].icon, contentDescription = items[0].contentDescription
            )
        }, selected = currentRoute == items[0].route, onClick = {

            if (currentRoute != null) {
                Log.d("currentRoute", currentRoute + "==" + items[0].route)

            }

            try {
                navController.navigate(items[0].route) {
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true

                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
//                                    inclusive = true
                        }
                        restoreState = true
                        // Restore state when reselecting a previously selected item
                    }

                }
            } catch (e: IllegalArgumentException) {

                Log.e("CATCH", items[0].route + " " + e.message.toString())

            }
        }

        )

        NavigationBarItem(icon = {
            Icon(
                imageVector = items[1].icon, contentDescription = items[1].contentDescription
            )
        }, selected = currentRoute == items[1].route, onClick = {
            try {
                if (idViewModel.getId() == 0) {
                    navController.navigate(Nothing.value) {
                        navController.graph.startDestinationRoute?.let { route ->
                            launchSingleTop = true
                            popUpTo(route) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                } else {
                    navController.navigate("detail_screen/${idViewModel.getId()}") {
                        launchSingleTop = true
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        restoreState = true
                    }
                }

            } catch (e: IllegalArgumentException) {
                Log.e("CATCH", items[1].route + " " + e.message.toString())
            }
        }

        )

        NavigationBarItem(icon = {
            Icon(
                imageVector = items[2].icon, contentDescription = items[2].contentDescription
            )
        }, selected = currentRoute == items[2].route, onClick = {

            try {
                navController.navigate(items[2].route) {
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    navController.graph.startDestinationRoute?.let { route ->
                        Log.d(
                            " navController.graph.startDestinationRoute ",
                            navController.graph.startDestinationRoute!!
                        )
                        popUpTo(route) {
                            saveState = true
//                                    inclusive = true
                        }
                    }

                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            } catch (e: IllegalArgumentException) {

                Log.e("CATCH", items[2].route + " " + e.message.toString())

            }
        }

        )

        NavigationBarItem(icon = {
            Icon(
                imageVector = items[3].icon, contentDescription = items[3].contentDescription
            )
        }, selected = currentRoute == items[3].route, onClick = {

            try {
                navController.navigate(items[3].route) {
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    navController.graph.startDestinationRoute?.let { route ->
                        Log.d(
                            " navController.graph.startDestinationRoute ",
                            navController.graph.startDestinationRoute!!
                        )
                        popUpTo(route) {
                            saveState = true
//                                    inclusive = true
                        }
                    }

                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            } catch (e: IllegalArgumentException) {

                Log.e("CATCH", items[3].route + " " + e.message.toString())

            }
        }

        )

//        items.forEach { item ->
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = item.contentDescription
//                    )
//                },
//                selected = currentRoute == item.route,
//                onClick = {
//
//                    try {
//
//                        if (item.route == "detail_screen/{id}") {
//                            item.route = "detail_screen"
//                        }
//                            navController.navigate(item.route) {
//                                navController.graph.startDestinationRoute?.let { route ->
//                                    popUpTo(route) {
//                                        saveState = true
////                                    inclusive = true
//                                    }
//                                }
//                                // Avoid multiple copies of the same destination when
//                                // reselecting the same item
//                                launchSingleTop = true
//                                // Restore state when reselecting a previously selected item
//                                restoreState = true
//                            }
//
//
//
//
//                    } catch (e: IllegalArgumentException) {
//
//                        Log.e("CATCH", item.route + " " + e.message.toString())
//
//                    }
//                }
//
//            )
//        }
    }
}

//@Preview
//@Composable
//fun Prev() {
//    val navController = rememberNavController()
//    TokoAppActivator(navController)
//
//}


