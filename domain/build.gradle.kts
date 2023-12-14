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

    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    implementation(Dev.Retrofit.retrofit2)
    implementation(Dev.Timber.timber)

}