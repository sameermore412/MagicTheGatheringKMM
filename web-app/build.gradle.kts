import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs() {
        moduleName = "magic"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val common by creating {
            dependencies {
//                implementation(project(":shared-ui"))
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("io.coil-kt.coil3:coil:3.0.0-alpha06")
                implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
                implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
                implementation("io.ktor:ktor-client-core:3.0.0-wasm2")
            }
        }
        val wasmJsMain by getting {
            dependsOn(common)
        }
    }
}