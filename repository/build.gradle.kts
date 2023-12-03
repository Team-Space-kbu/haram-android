plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlinx-serialization")

}

android {
    namespace = "com.space.repository"
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":shared"))

    //jetpack
    implementation(Dev.androidxSecurity)

    //dagger, hilt
    implementation(Dev.hiltAndroid)
    implementation(Dev.hiltAndroidGradle)
    kapt(Dev.hiltCompiler)

    //Retrofit, okhttp3
    implementation(Dev.gson)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation(Dev.okhttp3)
    implementation(Dev.okhttp3Logging)
    implementation(Dev.retrofit2)
    implementation(Dev.retrofit2ConverterScalars)
    implementation(Dev.retrofit2ConverterGson)

    implementation(Dev.timber)
}