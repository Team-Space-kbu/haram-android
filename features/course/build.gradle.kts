plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.course"
    buildFeatures {
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigate"))
    implementation(project(":shared"))
    implementation(project(":domain"))

    //KTX
    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Appcompat.androidAppcompat)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Facebook.Shimmer)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.fragment:fragment-ktx:1.8.2")
    kapt(Dev.Dagger.hiltCompiler)

    implementation(Dev.Timber.timber)

}