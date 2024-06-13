package com.project.toko.core.presentation_layer.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class Screen(val route: String, val contentDescription: String?) {

    data object Home : Screen("home_screen", "Search Anime")
    data object Detail : Screen("detail_screen/{id}", "Details on Anime")
    data object Favorites : Screen("favorites_screen", "Favorite anime")
    data object RandomAnimeOrManga :
        Screen("random_anime_or_manga_screen", "Current News on Anime")

//    data object Nothing : Screen("no_id", "No id screen")

    data object DetailOnWholeCast :
        Screen("detail_on_whole_cast/{id}", "Details on whole cast of current anime")

    data object DetailOnWholeStaff :
        Screen("detail_on_whole_staff", "Details on whole staff of current anime")

    data object CharacterDetail :
        Screen("detail_on_character/{id}", "Details on current character")

    data object StaffDetail : Screen("detail_on_staff/{id}", "Details on current staff member")

    data object HomeSubGraph : Screen("home_sub_graph", null)
//    data object DetailSubGraph : Screen("detail_sub_graph", null)
    data object DaoSubGraph : Screen("dao_sub_graph", null)
    data object RandomSubGraph : Screen("random_sub_graph", null)

    data object DetailSecond : Screen("detail_screen_second/{id}", "Details on Anime")
    data object DetailOnWholeCastSecond :
        Screen("detail_on_whole_cast_second/{id}", "Details on whole cast of current anime")
    data object DetailOnWholeStaffSecond :
        Screen("detail_on_whole_staff_second", "Details on whole staff of current anime")
    data object CharacterDetailSecond :
        Screen("detail_on_character_second/{id}", "Details on current character")
    data object StaffDetailSecond : Screen("detail_on_staff_second/{id}", "Details on current staff member")

    data object DetailThird: Screen("detail_screen/{id}", "Details on Anime")
    data object DetailOnWholeCastThird :
        Screen("detail_on_whole_cast/{id}", "Details on whole cast of current anime")
    data object DetailOnWholeStaffThird :
        Screen("detail_on_whole_staff", "Details on whole staff of current anime")
    data object CharacterDetailThird :
        Screen("detail_on_character/{id}", "Details on current character")
    data object StaffDetailThird : Screen("detail_on_staff/{id}", "Details on current staff member")

}