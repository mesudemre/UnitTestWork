package com.mesutemre.unittestwork.navigation

import androidx.annotation.StringRes
import com.mesutemre.unittestwork.R

sealed class UnitTestWorkNavigation(
    val screenRoute: String,
    val hasBack: Boolean = true,
    @StringRes val title: Int
) {
    data object MainScreen :
        UnitTestWorkNavigation(screenRoute = "unittestwork_main_screen", hasBack = false,
            title = R.string.main_screen_title)

    data object DetailScreen :
        UnitTestWorkNavigation(screenRoute = "unittestwork_detail_screen?itemId={itemId}",
            title = R.string.detail_screen_title)
}