package com.codelab.theming.ui.start.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    error = Red800
)

private val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = androidx.compose.ui.graphics.Color.Black,
    secondary = Red300,
    onSecondary = androidx.compose.ui.graphics.Color.Black,
    error = Red200
)


@Composable
fun JetnewsTheme(
    darkTheme : Boolean = isSystemInDarkTheme(),
    content : @Composable () -> Unit
){
    MaterialTheme(
        if (darkTheme) DarkColors else LightColors,
        JetnewsTypography,
        JetnewsShapes,
        content = content
    )
}


