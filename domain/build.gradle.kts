plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.domain"
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":data"))
    implementation(Dev.Retrofit.retrofit2)
    implementation(Dev.Timber.timber)

    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)
}