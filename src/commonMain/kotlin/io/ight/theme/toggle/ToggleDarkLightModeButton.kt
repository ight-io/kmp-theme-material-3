package io.ight.theme.toggle

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import io.ight.theme.IghtTheme
import io.ight.theme.Material3
import io.ight.theme.ThemeToggle


@Material3
internal val ToggleDarkLightModeButton : ThemeToggle = {
    var isDark by IghtTheme.isDark.current

    IconButton(
        onClick = { isDark = ! isDark }
    ) {
        Icon(
            if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode ,
            contentDescription = "Toggle brightness"
        )
    }

}
