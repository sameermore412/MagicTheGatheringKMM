pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") //Multiplatform compose repository
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental") //WASM experimental compose repo
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental") //WASM experimental compose repo
    }
}

rootProject.name = "MagicTheGatheringKMM"

include("desktop-app")
include("shared-ui")
include("shared-data")
include(":android-app")
