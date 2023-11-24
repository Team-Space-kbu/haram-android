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

    implementation(project(":shared"))
    implementation(project(":domain"))
    implementation(project(":data"))

    //KTX
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxNavigationFragment)

    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)


    //AndroidX
    implementation(Libraries.androidMaterial)
    implementation(Libraries.androidLifecycleViewmodel)
    implementation(Libraries.androidLifecycleLivedata)
    implementation(Libraries.androidRecyclerview)
}