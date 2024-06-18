package com.project.toko.homeScreen.data.model.linkChangerModel


data class Types(val typeName: String, var isSelected: Boolean = false)

fun getTypes(): List<Types> {
    return listOf(
        Types("tv"),
        Types("movie"),
        Types("ova"),
        Types("special"),
        Types("ona"),
        Types("music")
    )
}