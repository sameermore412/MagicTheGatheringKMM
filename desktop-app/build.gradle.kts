plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {}

    sourceSets {
        val voyagerVersion = "1.0.0-rc05"
        val jvmMain by getting {
            dependencies {
                implementation(project(":shared-ui"))
                implementation(compose.desktop.currentOs)
                implementation(compose.material)
                implementation(compose.foundation)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.more.mtg.MainKt"

        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = Constants.DesktopPackageName
            packageVersion = "1.0.0"

            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "AF792DA6-2EA3-495A-95E5-C3C6CBCB9948"
            }

            macOS {
                // Use -Pcompose.desktop.mac.sign=true to sign and notarize.
                bundleID = "com.more.desktopjvm.sample"
            }
        }
    }
}