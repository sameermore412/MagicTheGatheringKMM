plugins {
    // Setting plugin versions here so we don't have to do it somewhere else. "apply false" does not apply the plugin
    kotlin("multiplatform") version kotlinVersion apply false // Kotlin multiplatform plugin
    kotlin("native.cocoapods") version kotlinVersion apply false
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.compose") version kotlinVersion apply false
    kotlin("android") version kotlinVersion apply false

    id("com.android.application") version androidGradlePluginVersion apply false
    id("com.android.library") version androidGradlePluginVersion apply false
    id("org.jetbrains.compose") version composeVersion apply false
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental") //WASM experimental compose repo
    }
}