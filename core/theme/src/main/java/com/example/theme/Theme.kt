package com.example.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Dark,
    secondary = White,
    tertiary = LightBlack
)


@Composable
fun TestStudyAppTheme(
    content: @Composable () -> Unit
){
    val colorScheme = LightColorScheme
    MaterialTheme (
        colorScheme = colorScheme,
        typography = Typography,
        shapes = replyShapes,
        content = content
    )
}