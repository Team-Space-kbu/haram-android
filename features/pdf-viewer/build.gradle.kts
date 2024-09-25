plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.pdf_viewer"
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigate"))

    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Timber.timber)

    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    implementation("io.github.afreakyelf:Pdf-Viewer:2.1.1")
}