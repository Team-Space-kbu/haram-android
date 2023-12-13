plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlinx-serialization")
}

android {
    namespace = "com.space.shared"
}

dependencies {
    implementation(Dev.javaxInject)
    implementation(Dev.androidLifecycleLivedata)
    implementation(Dev.gson)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}