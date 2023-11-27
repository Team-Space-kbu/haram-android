plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.home"
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigator"))
    implementation(project(":features:book"))
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