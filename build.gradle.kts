import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(ight.plugins.kotlinMultiplatform)
    alias(ight.plugins.jetbrainsCompose)
    alias(ight.plugins.androidLibrary)
    alias(ight.plugins.compose.compiler)
    alias(ight.plugins.vanniktech.maven.publish)
}

kotlin {
    applyDefaultHierarchyTemplate()

    // ✅ WASM Target Configuration
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "io.ight.theme.wasm"
        browser {
            commonWebpackConfig {
                outputFileName = "material3.js"
            }
        }
        binaries.executable()
    }

    // ✅ JVM and Android Targets
    jvm("desktop")
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

//    ✅ Apple and Other Native Targets
//    val nativeTargets = listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64(),
//        macosX64(),
//        macosArm64()
//    )
//
//    nativeTargets.forEach {
//        it.binaries.framework {
//            baseName = "Material3"
//            isStatic = true // Ensures a static framework
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.components.resources)
                api(compose.components.uiToolingPreview)
                api(ight.material.kolor)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                api(ight.androidx.activity.compose)
                implementation(compose.uiTooling)
                api(compose.preview)
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.desktop.currentOs)
            }
        }

        val wasmJsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
    }
}

android {
    namespace = "io.ight.theme.android"
    compileSdk = ight.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = ight.versions.android.minSdk.get().toInt()
    }
    buildFeatures.compose = true
}
