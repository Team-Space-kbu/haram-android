plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlinx-serialization")
}

android {
    namespace = "com.space.shared"
}

dependencies {
    implementation(Dev.Gson.gson)
    implementation(Dev.Dagger.hiltAndroid)
    implementation(Dev.Kotlin.kotlinxSerializationJson)
    implementation(Dev.Kotlin.kotlinxSerializationConverter)

}