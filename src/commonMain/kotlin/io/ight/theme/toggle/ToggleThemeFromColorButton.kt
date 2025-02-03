package io.ight.theme.toggle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import io.ight.theme.AppColor.Seed
import io.ight.theme.AppColor.Seed.SeedColor
import io.ight.theme.IghtTheme
import io.ight.theme.IghtTheme.Shape.RoundedCorner
import io.ight.theme.ThemeDsl
import io.ight.theme.ThemeToggle


@ThemeDsl
internal val ToggleThemeFromColorButton : ThemeToggle = {
    val appColorState = IghtTheme.appColor.current
    val appColor = appColorState.value
    val selectedSeedColor = (appColor as? Seed)?.seedColor

    fun onSelected(color: SeedColor) {
        appColorState.value = Seed(color)
    }

    var isSeedChooserOpen by remember { mutableStateOf(false) }
    IconButton(
        onClick = { isSeedChooserOpen = !isSeedChooserOpen }
    ) {
        Icon(
            Icons.Default.Palette,
            contentDescription = "Select a seed color"
        )
    }
    if (isSeedChooserOpen) {
        Popup(onDismissRequest = { isSeedChooserOpen = false }) {
            Column(
                Modifier
                    .shadow(16.dp, RoundedCorner.l)
                    .clip(RoundedCorner.l)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 8.dp)
                    .width(IntrinsicSize.Max)
            ) {
                SeedColor.entries.forEach { color ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedSeedColor != color) {
                                onSelected(color)
                                isSeedChooserOpen = false
                            }
                            .then(if (selectedSeedColor == color) Modifier.alpha(0.6f) else Modifier)
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = if (selectedSeedColor == color) {
                                Icons.Filled.Palette
                            } else {
                                Icons.Outlined.Palette
                            },
                            contentDescription = "Select a seed color",
                            tint = color.value
                        )
                        Text(color.colorName, Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}
