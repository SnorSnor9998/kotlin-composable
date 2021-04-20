import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    id("org.jetbrains.compose") version "0.3.2"
    id("com.apollographql.apollo").version("2.5.5")
}

group = "me.sianloong"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    maven { url = uri("https://jitpack.io") }

}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.apollographql.apollo:apollo-runtime:2.5.5")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.5.5")
}

apollo {
    generateKotlinModels.set(true)
    customTypeMapping.putAll(mapOf("DateTime" to "java.util.Date"))
    customTypeMapping.putAll(mapOf("Date" to "java.util.Date"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb,TargetFormat.Exe)
            packageName = "POS"
            packageVersion = "1.0.0"


            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "6565BEAD-713A-4DE7-A469-6B10FC4A6861"
            }
        }
    }
}