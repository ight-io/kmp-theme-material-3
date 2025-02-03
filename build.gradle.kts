import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(ight.plugins.kotlinMultiplatform)
    alias(ight.plugins.jetbrainsCompose)
    alias(ight.plugins.androidLibrary)
    alias(ight.plugins.compose.compiler)
    alias(ight.plugins.vanniktech.maven.publish)
}

kotlin {


    applyDefaultHierarchyTemplate()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "io.ight.theme.wasm"
        browser {
            commonWebpackConfig {
                outputFileName = "theme.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.rootDir.path)
                        add(project.projectDir.path)
                    }
                }
            }
        }
    }


    jvm("desktop")
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }


    sourceSets {


        val desktopMain by getting

        commonMain {
            dependencies {
                api(compose.runtime)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.components.resources)
                api(compose.components.uiToolingPreview)
                api(ight.material.kolor)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        androidMain {
            dependencies {
                api(ight.androidx.activity.compose)
                implementation(compose.uiTooling)
                api(compose.preview)
            }
        }

        desktopMain.dependencies {
            api(compose.desktop.currentOs)
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
