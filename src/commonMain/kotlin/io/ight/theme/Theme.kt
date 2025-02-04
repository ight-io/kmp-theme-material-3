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
annotation class Material3


@Material3
data object IghtTheme {


    @Material3
    val isDark = compositionLocalOf { mutableStateOf(true) }


    @Material3
    val appColor = compositionLocalOf { mutableStateOf<AppColor>(Seed(BASELINE)) }


    @Material3
    data object Toggle {


        @Material3
        val darkLightMode : ThemeToggle = ToggleDarkLightModeButton


        @Material3
        val themeFromImage : ThemeToggle = ToggleThemeFromImageButton


        @Material3
        val themeFromSeed : ThemeToggle = ToggleThemeFromColorButton

    }


    @Material3
    data object FontSize {


        @Material3
        val xs = 12.sp


        @Material3
        val s = 14.sp


        @Material3
        val m = 16.sp


        @Material3
        val l = 18.sp


        @Material3
        val xl = 20.sp

    }


    @Material3
    data object Elevation {

    }


    @Material3
    data object Shape {


        @Material3
        data object RoundedCorner {


            @Material3
            val xs = RoundedCornerShape(2.dp)


            @Material3
            val s = RoundedCornerShape(4.dp)


            @Material3
            val m = RoundedCornerShape(8.dp)


            @Material3
            val l = RoundedCornerShape(16.dp)


            @Material3
            val xl = RoundedCornerShape(32.dp)

        }

    }


    @Composable
    @Material3
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
@Material3
expect fun SystemAppearance(isDark : Boolean)
