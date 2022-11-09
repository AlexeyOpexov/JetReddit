package com.example.jetreddit.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LightThemeColors = lightColors(
    primary = RwPrimary,
    primaryVariant = RwPrimaryDark,
    onPrimary = Color.Gray,
    secondary = Color.LightGray,
    secondaryVariant = RwPrimaryDark,
    onSecondary = Color.Black,
    error = Red800
)

@SuppressLint("ConflictingOnColor")
private val DarkThemeColors = darkColors(
    primary = RwPrimaryDark,
    primaryVariant = RwPrimary,
    onPrimary = Color.Gray,
    secondary = Color.Black,
    onSecondary = Color.White,
    error = Red800
)

@Composable
fun JetRedditTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (JetRedditThemeSettings.isInDarkTheme.value) DarkThemeColors else LightThemeColors

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object JetRedditThemeSettings {
    var isInDarkTheme: MutableState<Boolean> = mutableStateOf(false)
}