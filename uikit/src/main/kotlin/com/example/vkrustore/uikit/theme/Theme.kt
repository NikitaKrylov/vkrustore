package com.example.vkrustore.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val RuStoreDarkColorScheme = darkColorScheme(
    primary = Color(0xFF0072E5),
    onPrimary = Color(0xFFEBF1F6),
    primaryContainer = Color(0xFF1A73E8),
    onPrimaryContainer = Color(0xFFE5F1FF),

    secondary = Color(0xFF2F90ED),
    onSecondary = Color(0xFF0D1B2A),
    secondaryContainer = Color(0xFF132E4B),
    onSecondaryContainer = Color(0xFF2F90ED),

    tertiary = Color(0xFF4BB34B),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFF336B33),
    onTertiaryContainer = Color(0xFFFFFFFF),

    background = Color(0xFF0E1116),
    onBackground = Color(0xFFE8EAED),

    surface = Color(0xFF171C22),
    onSurface = Color(0xFFD2D7DD),

    surfaceContainer = Color(0xFF171C22),

    surfaceVariant = Color(0xFF24282E),
    onSurfaceVariant = Color(0xFF9AA0A6),

    outline = Color(0xFF3C4043),
    outlineVariant = Color(0xFF21262C),

    error = Color(0xFFFF3347),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFF93000F),
    onErrorContainer = Color(0xFFFFFFFF),

    inverseSurface = Color(0xFF343844),
    inverseOnSurface = Color(0xFFEAEFF1),

    inversePrimary = Color(0xFF6699FF)
)
private val RuStoreLightColorScheme = lightColorScheme(
    primary = Color(0xFF0077FF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE5F1FF),
    onPrimaryContainer = Color(0xFF1A73E8),

    secondary = Color(0xFF1871DD),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC9E2FF),
    onSecondaryContainer = Color(0xFF1871DD),

    tertiary = Color(0xFF4BB34B),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE4F4E4),
    onTertiaryContainer = Color(0xFF246B24),

    background = Color(0xFFF2F3F5),
    onBackground = Color(0xFF1F1F1F),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1F2022),

    surfaceContainer = Color(0xFFffffff),

    surfaceVariant = Color(0xFFF1F3F4),
    onSurfaceVariant = Color(0xFF686B70),

    outline = Color(0xFF99A2AD),
    outlineVariant = Color(0xFFF5F5F5),

    error = Color(0xFFFF3347),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFAEBEB),
    onErrorContainer = Color(0xFF93000F),

    inverseSurface = Color(0xFF2B313A),
    inverseOnSurface = Color(0xFFF3F7F8),
    inversePrimary = Color(0xFF8AB4F8)
)
private val LightColorScheme = RuStoreLightColorScheme

private val DarkColorScheme = RuStoreDarkColorScheme


@Composable
fun VKRuStoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme.takeIf {
        darkTheme
    } ?: LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}