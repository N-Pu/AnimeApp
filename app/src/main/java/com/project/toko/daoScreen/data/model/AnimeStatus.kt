package com.project.toko.daoScreen.data.model
enum class AnimeStatus(
    val route: String,
) {
    WATCHING("Watching"),
    PLANNED("Planned"),
    COMPLETED("Completed"),
    DROPPED("Dropped"),
    FAVORITE("Favorite"),
    PERSON("Person"),
    CHARACTER("Character")
}
