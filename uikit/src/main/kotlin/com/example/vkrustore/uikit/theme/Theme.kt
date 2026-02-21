package com.example.vkrustore.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val RuStoreDarkColorScheme = darkColorScheme(
    primary = Color(0xFF0072E5),                // core-accent-blue250
    onPrimary = Color(0xFFEBF1F6),              // core-text-base90 (светлый на акцент)
    primaryContainer = Color(0xFF004BB3),       // более тёмный акцент контейнера
    onPrimaryContainer = Color(0xFFFFFFFF),

    secondary = Color(0xFF3681C1),              // core-surface-accent
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF003BBF),     // тёмный вторичный контейнер
    onSecondaryContainer = Color(0xFFFFFFFF),

    tertiary = Color(0xFF4BB34B),               // позитивный светлый акцент
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFF336B33),      // тёмный позитивный контейнер
    onTertiaryContainer = Color(0xFFFFFFFF),

    background = Color(0xFF171C22),             // core-surface-level05
    onBackground = Color(0xFFEBF1F6),           // core-text-base90

    surface = Color(0xFF232A31),                // core-surface-primary-dark
    onSurface = Color(0xFFEBF1F6),

    surfaceVariant = Color(0xFF2B323D),         // core-surface-level20
    onSurfaceVariant = Color(0xFF9BA6B1),       // core-text-secondary-dark

    outline = Color(0xFF99A2AD),                // core-text-tertiary (тёмный)

    error = Color(0xFFFF3347),                  // core-surface-negative
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFF93000F),
    onErrorContainer = Color(0xFFFFFFFF),

    inverseSurface = Color(0xFFFFFFFF),
    inverseOnSurface = Color(0xFF1A1C20),

    inversePrimary = Color(0xFF6699FF)          // нейтральный контрастный акцент
)
private val RuStoreLightColorScheme = lightColorScheme(
    primary = Color(0xFF0072E5),                // core-accent-blue250
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE5F1FF),       // core-surface-accentSubdued
    onPrimaryContainer = Color(0xFF0072E5),

    secondary = Color(0xFF1D76D2),              // core-surface-accent
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE5F1FF),
    onSecondaryContainer = Color(0xFF004DB3),   // более тёмный акцент

    tertiary = Color(0xFF4BB34B),               // core-surface-positive
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE4F4E4),      // core-surface-positiveSubdued
    onTertiaryContainer = Color(0xFF246B24),

    background = Color(0xFFFFFFFF),             // background-primary
    onBackground = Color(0xFF222222),           // core-text-primary

    surface = Color(0xFFF6F5F5),                // core-surface-primary
    onSurface = Color(0xFF222222),

    surfaceVariant = Color(0xFFF7F7F7),         // core-surface-secondary
    onSurfaceVariant = Color(0xFF6D7885),       // core-text-secondary

    outline = Color(0xFF99A2AD),                // core-text-tertiary

    error = Color(0xFFFF3347),                  // core-surface-negative
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFAEBEB),         // core-surface-negativeSubdued
    onErrorContainer = Color(0xFF93000F),

    inverseSurface = Color(0xFF1A1C20),         // core-surface-primary-dark
    inverseOnSurface = Color(0xFFEBF1F6)        // core-text-base90 / dark text on inverseSurface
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