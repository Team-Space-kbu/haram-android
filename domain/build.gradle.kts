plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.domain"
}

dependencies {
    implementation(project(":builder-annotation"))
    implementation(project(":shared"))
    implementation(project(":data"))
    implementation(Dev.Retrofit.retrofit2)
    implementation(Dev.Timber.timber)
    implementation(Dev.Gson.gson)

    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)
}