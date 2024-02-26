plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.bible"
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
    implementation(project(":features:navigate"))
    implementation(project(":shared"))
    implementation(project(":domain"))

    //KTX
    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Facebook.shimmer)
    implementation(Dev.Timber.timber)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

}