package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = Color(0xff2B6ED0),
    primaryVariant = Color(0xff2B6ED0),
    secondary = Color(0xff2B6ED0),
    secondaryVariant = Color(0xff2B6ED0),
    background = Color.White,
    onSecondary = Color.White,
)

@Composable
internal fun RayleighTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ColorPalette,
        content = content
    )
}
