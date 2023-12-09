plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.other"
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":shared"))
    implementation(project(":domain"))
    //KTX
    implementation(Dev.androidxCore)
    implementation(Dev.androidxNavigationFragment)

    //dagger, hilt
    implementation(Dev.hiltAndroid)
    kapt(Dev.hiltCompiler)

    //AndroidX
    implementation(Dev.androidMaterial)
    implementation(Dev.androidLifecycleViewmodel)
    implementation(Dev.androidLifecycleLivedata)
    implementation(Dev.androidRecyclerview)
}