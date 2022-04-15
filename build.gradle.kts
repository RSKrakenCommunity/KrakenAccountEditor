plugins {
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.20"
    id("org.openjfx.javafxplugin") version "0.0.12"
    id("org.beryx.runtime") version "1.12.7"
    application
}

group = "com.rshub"
version = "0.0.2"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

application {
    applicationName = "Kraken Account Editor"
    mainClass.set("com.rshub.Application")
}

runtime {
    options.set(listOf("--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        noConsole = true
    }
    jpackage {
        imageOptions = listOf("--icon", "src/main/resources/icon.ico")
        installerName = "Kraken Account Editor"
        installerOptions = listOf(
            "--vendor",
            "Kraken Community",
            "--win-menu",
            "--win-shortcut",
            "--resource-dir",
            "src/main/resources",
            "--win-per-user-install",
            "--win-dir-chooser"
        )
        isSkipInstaller = false
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.3.2")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.20")
}