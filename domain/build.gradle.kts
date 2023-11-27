plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.domain"
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":repository"))

    //dagger, hilt
    implementation(Dev.hiltAndroid)
    implementation(Dev.hiltAndroidGradle)
    kapt(Dev.hiltCompiler)

    //Retrofit, okhttp3
    implementation(Dev.gson)
    implementation(Dev.okhttp3)
    implementation(Dev.okhttp3Logging)
    implementation(Dev.retrofit2)
    implementation(Dev.retrofit2ConverterScalars)
    implementation(Dev.retrofit2ConverterGson)

    //etc
    implementation(Dev.jsoup)
    implementation(Dev.timber)

}