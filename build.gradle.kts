import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.compose") version "0.1.0-m1-build62"
    application
}

group = "me.gmasalskih"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.all)
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.firebase:firebase-admin:7.0.1")
    implementation("com.google.cloud:google-cloud-storage:1.113.2")
    implementation("org.koin:koin-core:2.1.6")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}