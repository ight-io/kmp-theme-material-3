package io.ight.theme

import androidx.compose.ui.graphics.Color
import ight_theme.generated.resources.Res.drawable
import ight_theme.generated.resources.content_based_color_scheme_1
import ight_theme.generated.resources.content_based_color_scheme_2
import ight_theme.generated.resources.content_based_color_scheme_3
import ight_theme.generated.resources.content_based_color_scheme_4
import ight_theme.generated.resources.content_based_color_scheme_5
import ight_theme.generated.resources.content_based_color_scheme_6
import org.jetbrains.compose.resources.DrawableResource

@ThemeDsl
sealed interface AppColor {

    @ThemeDsl
    val color : Color
    @ThemeDsl
    data class Seed(val seedColor : SeedColor) : AppColor {

        @ThemeDsl
        override val color : Color = seedColor.value
        @ThemeDsl
        enum class SeedColor(
            @ThemeDsl
            val colorName : String ,
            @ThemeDsl
            val value : Color ,
        ) {


            @ThemeDsl
            BASELINE("M3 Baseline" , Color(0xFF6750A4)) ,
            @ThemeDsl
            INDIGO("Indigo" , Color(0xFF_3F51B5)) ,
            @ThemeDsl
            BLUE("Blue" , Color(0xFF_0061A4)) ,
            @ThemeDsl
            TEAL("Teal" , Color(0xFF_009688)) ,
            @ThemeDsl
            GREEN("Green" , Color(0xFF_4CAF50)) ,
            @ThemeDsl
            YELLOW("Yellow" , Color(0xFF_FFEB3B)) ,
            @ThemeDsl
            ORANGE("Orange" , Color(0xFF_FF9800)) ,
            @ThemeDsl
            DEEP_ORANGE("Deep orange" , Color(0xFF_FF5722)) ,
            @ThemeDsl
            PINK("Pink" , Color(0xFF_E91E63)) ,
        }

    }
    @ThemeDsl
    data class Image(
        @ThemeDsl
        val image : ColorExtractionImage ,
        @ThemeDsl
        override val color : Color ,
    ) : AppColor {

        @ThemeDsl
        enum class ColorExtractionImage(
            @ThemeDsl
            val imageName : String ,
            @ThemeDsl
            val imageResource : DrawableResource ,
        ) {


            @ThemeDsl
            LEAVES("Leaves" , drawable.content_based_color_scheme_1) ,
            @ThemeDsl
            PEONIES("Peonies" , drawable.content_based_color_scheme_2) ,
            @ThemeDsl
            BUBBLES("Bubbles" , drawable.content_based_color_scheme_3) ,
            @ThemeDsl
            SEAWEED("Seaweed" , drawable.content_based_color_scheme_4) ,
            @ThemeDsl
            SEA_GRAPES("Sea Grapes" , drawable.content_based_color_scheme_5) ,
            @ThemeDsl
            PETALS("Petals" , drawable.content_based_color_scheme_6) ,
        }

    }
}