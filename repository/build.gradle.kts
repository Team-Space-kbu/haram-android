plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
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
    implementation(Libraries.androidxSecurity)

    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    implementation(Libraries.hiltAndroidGradle)
    kapt(Libraries.hiltCompiler)

    //Retrofit, okhttp3
    implementation(Libraries.gson)
    implementation(Libraries.okhttp3)
    implementation(Libraries.okhttp3Logging)
    implementation(Libraries.retrofit2)
    implementation(Libraries.retrofit2ConverterScalars)
    implementation(Libraries.retrofit2ConverterGson)

    implementation(Libraries.timber)
}