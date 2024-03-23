// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    kotlin("plugin.serialization") version "1.5.0"
    kotlin("kapt") version "1.9.23"
    id("org.jetbrains.kotlin.jvm") version "1.9.22" apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath("com.google.android.gms:oss-licenses-plugin:0.10.6")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}