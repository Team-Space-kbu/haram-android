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
    implementation(project(":data"))
    //jetpack
    implementation(Libraries.androidxSecurity)
    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    implementation(Libraries.hiltAndroidGradle)
    kapt(Libraries.hiltCompiler)

    implementation(Libraries.timber)
}