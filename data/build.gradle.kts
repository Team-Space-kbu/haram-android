plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.data"
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":security"))
    implementation(Dev.Dagger.hiltAndroid)
    implementation(Dev.Dagger.hiltAndroidGradle)
    kapt(Dev.Dagger.hiltCompiler)
    implementation(Dev.Gson.gson)
    implementation(Dev.Retrofit.retrofit2)
    implementation(Dev.Retrofit.retrofit2ConverterScalars)
    implementation(Dev.Retrofit.retrofit2ConverterGson)
    implementation(Dev.Kotlin.kotlinxSerializationConverter)
    implementation(Dev.Kotlin.kotlinxSerializationJson)
    implementation(Dev.Okhttp.okhttp3)
    implementation(Dev.Okhttp.okhttp3Logging)
    implementation(Dev.Timber.timber)
}