package io.ight.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.materialkolor.dynamicColorScheme
import io.ight.theme.AppColor.Image
import io.ight.theme.AppColor.Seed
import io.ight.theme.AppColor.Seed.SeedColor.BASELINE
import io.ight.theme.IghtTheme.Shape.RoundedCorner
import io.ight.theme.toggle.ToggleThemeFromImageButton
import io.ight.theme.toggle.ToggleThemeFromColorButton
import io.ight.theme.toggle.ToggleDarkLightModeButton


typealias ThemeToggle = @Composable () -> Unit

@DslMarker
annotation class ThemeDsl


@ThemeDsl
data object IghtTheme {


    @ThemeDsl
    val isDark = compositionLocalOf { mutableStateOf(true) }


    @ThemeDsl
    val appColor = compositionLocalOf { mutableStateOf<AppColor>(Seed(BASELINE)) }


    @ThemeDsl
    data object Toggle {


        @ThemeDsl
        val darkLightMode : ThemeToggle = ToggleDarkLightModeButton


        @ThemeDsl
        val themeFromImage : ThemeToggle = ToggleThemeFromImageButton


        @ThemeDsl
        val themeFromSeed : ThemeToggle = ToggleThemeFromColorButton

    }


    @ThemeDsl
    data object FontSize {


        @ThemeDsl
        val xs = 12.sp


        @ThemeDsl
        val s = 14.sp


        @ThemeDsl
        val m = 16.sp


        @ThemeDsl
        val l = 18.sp


        @ThemeDsl
        val xl = 20.sp

    }


    @ThemeDsl
    data object Elevation {

    }


    @ThemeDsl
    data object Shape {


        @ThemeDsl
        data object RoundedCorner {


            @ThemeDsl
            val xs = RoundedCornerShape(2.dp)


            @ThemeDsl
            val s = RoundedCornerShape(4.dp)


            @ThemeDsl
            val m = RoundedCornerShape(8.dp)


            @ThemeDsl
            val l = RoundedCornerShape(16.dp)


            @ThemeDsl
            val xl = RoundedCornerShape(32.dp)

        }

    }


    @Composable
    @ThemeDsl
    operator fun invoke(content : @Composable () -> Unit) {

        val systemIsDark = isSystemInDarkTheme()
        val isDarkState = remember { mutableStateOf(systemIsDark) }
        val appColorState = remember { mutableStateOf<AppColor>(Seed(BASELINE)) }
        val appColor = appColorState.value

        CompositionLocalProvider(
            IghtTheme.isDark provides isDarkState ,
            IghtTheme.appColor provides appColorState ,
        ) {
            val isDark by isDarkState
            SystemAppearance(! isDark)
            MaterialTheme(
                colorScheme = dynamicColorScheme(
                    seedColor = remember(appColor) {
                        when (appColor) {
                            is Seed -> appColor.seedColor.value
                            is Image -> appColor.color
                        }
                    } ,
                    isDark = isDark ,
                    isAmoled = true ,
//                    primary = ,
//                    secondary = ,
//                    tertiary = ,
//                    neutral =  ,
//                    neutralVariant =  ,
//                    error = ,
//                    style = ,
//                    contrastLevel = ,
                    isExtendedFidelity = true ,
//                    modifyColorScheme = ,
                ) ,
                typography = Typography(
//                    displayLarge =  ,
//                    displayMedium =  ,
//                    displaySmall =  ,
//                    headlineLarge =  ,
//                    headlineMedium =  ,
//                    headlineSmall =  ,
//                    titleLarge =  ,
//                    titleMedium = ,
//                    titleSmall =  ,
//                    bodyLarge = ,
                    bodyMedium = TextStyle(
                        fontFamily = FontFamily.Default ,
                        fontWeight = FontWeight.Medium ,
                        fontSize = FontSize.m
                    ) ,
//                    bodySmall =,
//                    labelLarge = ,
//                    labelMedium = ,
//                    labelSmall = ,
                ) ,
                shapes = Shapes(
                    extraSmall = RoundedCorner.xs ,
                    small = RoundedCorner.s ,
                    medium = RoundedCorner.m ,
                    large = RoundedCorner.l ,
                    extraLarge = RoundedCorner.xl
                ) ,
                content = {
                    Surface(content = content)
                }
            )
        }
    }
}


@Composable
@ThemeDsl
expect fun SystemAppearance(isDark : Boolean)
