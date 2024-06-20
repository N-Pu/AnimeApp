package com.project.toko.core.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

// if user taps to tab when he's on it - stack pops to the start screen
fun navigateToTab(
    navController: NavController,
    rootScreen: RootScreen,
    currentSelectedScreen: RootScreen,
    leafScreen: LeafScreen
) {
    try {
        if (
            currentSelectedScreen.route == rootScreen.route
        ) {
            navController.navigate(rootScreen.route) {
                popUpTo(leafScreen.route) {
                    inclusive = false // исключаем первый экран из удаления
                }
                launchSingleTop = true
            }
        } else {
            // Navigate to the target route and clear the back stack for that route
            navController.navigate(rootScreen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
        checkBackStack(navController)

    } catch (e: Exception) {
        Log.e("CATCH", "${rootScreen.route} -> ${e.message}")
    }
}

@SuppressLint("RestrictedApi")
private fun checkBackStack(navController: NavController) = run {
    Log.d(
        "root destination parent ->",
        navController.graph.findStartDestination().parent?.route.toString()
    )
    Log.d(
        "root destination ->",
        navController.graph.findStartDestination().route.toString()
    )
    Log.d(
        "currentBackStack -> -----------------------------------------------",
        "------------------------"
    )
    navController.currentBackStack.value.forEach {
        Log.d("currentBackStack -> ", it.destination.route + " - " + it.id)
    }
    Log.d(
        "currentBackStack -> -----------------------------------------------",
        "------------------------"
    )
}

