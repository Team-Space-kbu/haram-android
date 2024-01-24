plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.security"


}

dependencies {
    implementation(project(":shared"))

    //jetpack
    implementation(Dev.Android.Security.androidxSecurity)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    implementation(Dev.Dagger.hiltAndroidGradle)
    kapt(Dev.Dagger.hiltCompiler)


    implementation(Dev.Timber.timber)
}