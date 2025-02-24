package com.junior.lst_1995.bottombardemo

sealed class NavRoute(val route: String) {
    data object Home : NavRoute("home")
    data object Contacts : NavRoute("contacts")
    data object Favorites: NavRoute("favorites")
}