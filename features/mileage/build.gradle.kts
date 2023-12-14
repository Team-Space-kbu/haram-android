plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.space.mileage"
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
    implementation(project(":shared"))
    implementation(project(":domain"))

    //KTX
    implementation(Dev.Android.androidxCore)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)
}