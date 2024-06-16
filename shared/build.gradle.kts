plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-kapt")

}

android {
    namespace = "com.space.shared"
}

dependencies {
    implementation(Dev.Gson.gson)
    implementation(Dev.Kotlin.kotlinxSerializationJson)
    implementation(Dev.Kotlin.kotlinxSerializationConverter)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)


    implementation(Dev.Timber.timber)
    implementation("javax.inject:javax.inject:1")
}