plugins {
    kotlin("jvm") version "1.6.10"
    id("org.openjfx.javafxplugin") version "0.0.12"
}

group = "com.rshub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.3.2")
}