package com.project.toko.core.ui.main


import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.project.toko.core.ui.appConstraction.AppActivator
import com.project.toko.core.ui.theme.SplashTheme
import com.project.toko.core.ui.theme.Theme
import com.project.toko.core.data.settings.SaveDarkMode
import com.project.toko.splashScreen.AnimatedSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    lateinit var navController: NavHostController

    @Inject
    lateinit var svgImageLoader: ImageLoader

    private lateinit var darkTheme: SaveDarkMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // locked screen for the time being

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        darkTheme = SaveDarkMode(this)
        darkTheme.loadData()


        setContent {
//            val loadNSFW : HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
//            loadNSFW.loadNSFWData()
            val systemUiController = rememberSystemUiController(window)
            val splashShown = remember { mutableStateOf(false) }
            navController = rememberNavController()
            if (!splashShown.value) {

                systemUiController.isNavigationBarVisible = false
                SplashTheme(
                    darkTheme = darkTheme.isDarkThemeActive.value,
                    systemUiController = systemUiController
                ) {
                    AnimatedSplashScreen(navController, svgImageLoader) {
                        splashShown.value = true
                    }
                }
            } else {
                systemUiController.isNavigationBarVisible = true
                Theme(
                    darkTheme = darkTheme.isDarkThemeActive.value,
                    systemUiController = systemUiController
                ) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                            .fillMaxSize(),
                    ) {
                        AppActivator(
                            navController = navController,
                            modifier = Modifier,
                            componentActivity = this,
                            onThemeChange = {
                                darkTheme.isDarkThemeActive.value =
                                    !darkTheme.isDarkThemeActive.value
                                darkTheme.saveData(darkTheme.isDarkThemeActive.value)
                            },
                            isInDarkTheme = { darkTheme.isDarkThemeActive.value },
                            svgImageLoader = svgImageLoader
                        )
                    }
                }
            }
        }
    }
}
