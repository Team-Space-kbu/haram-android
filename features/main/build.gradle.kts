plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.main"
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:home"))
    implementation(project(":features:board"))
    implementation(project(":features:other"))
    implementation(project(":features:navigate"))

    //KTX
    implementation(Dev.androidxCore)
    implementation(Dev.androidxNavigationFragment)
    implementation(Dev.androidxNavigationUi)
    implementation(Dev.androidConstraintlayout)


    //dagger, hilt
    implementation(Dev.hiltAndroid)
    kapt(Dev.hiltCompiler)


    //AndroidX
    implementation(Dev.androidMaterial)
    implementation(Dev.androidLifecycleViewmodel)
    implementation(Dev.androidLifecycleLivedata)
    implementation(Dev.androidRecyclerview)
}