package com.project.toko.core.ui.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class RootScreen(val route: String) {
    data object HomeSubGraph : RootScreen("home_sub_graph")
    data object DaoSubGraph : RootScreen("dao_sub_graph")
    data object RandomSubGraph : RootScreen("random_sub_graph")
}

@Immutable
sealed class LeafScreen(val route: String, val contentDescription: String?) {
    data object Home : LeafScreen("home_screen", "Search Anime")
    data object Favorites : LeafScreen("favorites_screen", "Favorite anime")
    data object RandomAnimeOrManga :
        LeafScreen("random_anime_or_manga_screen", "Current News on Anime")


    data object DetailHome : LeafScreen("detail_screen_home/{id}", "Details on Anime")
    data object DetailDao : LeafScreen("detail_screen_dao/{id}", "Details on Anime")
    data object DetailRandom : LeafScreen("detail_screen_random/{id}", "Details on Anime")


    data object DetailOnWholeCastHome :
        LeafScreen("detail_on_whole_cast_home/{id}", "Details on whole cast of current anime")

    data object DetailOnWholeCastDao :
        LeafScreen("detail_on_whole_cast_dao/{id}", "Details on whole cast of current anime")

    data object DetailOnWholeCastRandom :
        LeafScreen("detail_on_whole_cast_random/{id}", "Details on whole cast of current anime")


    data object DetailOnWholeStaffHome :
        LeafScreen("detail_on_whole_staff_home", "Details on whole staff of current anime")

    data object DetailOnWholeStaffDao :
        LeafScreen("detail_on_whole_staff_dao", "Details on whole staff of current anime")

    data object DetailOnWholeStaffRandom :
        LeafScreen("detail_on_whole_staff_random", "Details on whole staff of current anime")


    data object CharacterDetailHome :
        LeafScreen("detail_on_character_home/{id}", "Details on current character")

    data object CharacterDetailDao :
        LeafScreen("detail_on_character_dao/{id}", "Details on current character")

    data object CharacterDetailRandom :
        LeafScreen("detail_on_character_random/{id}", "Details on current character")


    data object StaffDetailHome :
        LeafScreen("detail_on_staff_home/{id}", "Details on current staff member")

    data object StaffDetailDao :
        LeafScreen("detail_on_staff_dao/{id}", "Details on current staff member")

    data object StaffDetailRandom :
        LeafScreen("detail_on_staff_random/{id}", "Details on current staff member")

}
