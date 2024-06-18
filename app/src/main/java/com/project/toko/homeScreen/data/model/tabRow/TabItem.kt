package com.project.toko.homeScreen.data.model.tabRow

data class TabItem(
    val title: String,
)

fun returnListOfTabItems(): List<com.project.toko.homeScreen.data.model.tabRow.TabItem> {
    return listOf(
        com.project.toko.homeScreen.data.model.tabRow.TabItem("Type"),
        com.project.toko.homeScreen.data.model.tabRow.TabItem("Genres"),
        com.project.toko.homeScreen.data.model.tabRow.TabItem("Rating"),
        com.project.toko.homeScreen.data.model.tabRow.TabItem("Score"),
        com.project.toko.homeScreen.data.model.tabRow.TabItem("Order By")
    )
}

