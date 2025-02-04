package io.ight.theme

import androidx.compose.ui.graphics.Color
import kmp_theme_material_3.generated.resources.Res.drawable
import kmp_theme_material_3.generated.resources.content_based_color_scheme_1
import kmp_theme_material_3.generated.resources.content_based_color_scheme_2
import kmp_theme_material_3.generated.resources.content_based_color_scheme_3
import kmp_theme_material_3.generated.resources.content_based_color_scheme_4
import kmp_theme_material_3.generated.resources.content_based_color_scheme_5
import kmp_theme_material_3.generated.resources.content_based_color_scheme_6
import org.jetbrains.compose.resources.DrawableResource

@Material3
sealed interface AppColor {

    @Material3
    val color : Color
    @Material3
    data class Seed(val seedColor : SeedColor) : AppColor {

        @Material3
        override val color : Color = seedColor.value
        @Material3
        enum class SeedColor(
            @Material3
            val colorName : String ,
            @Material3
            val value : Color ,
        ) {


            @Material3
            BASELINE("M3 Baseline" , Color(0xFF6750A4)) ,
            @Material3
            INDIGO("Indigo" , Color(0xFF_3F51B5)) ,
            @Material3
            BLUE("Blue" , Color(0xFF_0061A4)) ,
            @Material3
            TEAL("Teal" , Color(0xFF_009688)) ,
            @Material3
            GREEN("Green" , Color(0xFF_4CAF50)) ,
            @Material3
            YELLOW("Yellow" , Color(0xFF_FFEB3B)) ,
            @Material3
            ORANGE("Orange" , Color(0xFF_FF9800)) ,
            @Material3
            DEEP_ORANGE("Deep orange" , Color(0xFF_FF5722)) ,
            @Material3
            PINK("Pink" , Color(0xFF_E91E63)) ,
        }

    }
    @Material3
    data class Image(
        @Material3
        val image : ColorExtractionImage ,
        @Material3
        override val color : Color ,
    ) : AppColor {

        @Material3
        enum class ColorExtractionImage(
            @Material3
            val imageName : String ,
            @Material3
            val imageResource : DrawableResource ,
        ) {


            @Material3
            LEAVES("Leaves" , drawable.content_based_color_scheme_1) ,
            @Material3
            PEONIES("Peonies" , drawable.content_based_color_scheme_2) ,
            @Material3
            BUBBLES("Bubbles" , drawable.content_based_color_scheme_3) ,
            @Material3
            SEAWEED("Seaweed" , drawable.content_based_color_scheme_4) ,
            @Material3
            SEA_GRAPES("Sea Grapes" , drawable.content_based_color_scheme_5) ,
            @Material3
            PETALS("Petals" , drawable.content_based_color_scheme_6) ,
        }

    }
}
