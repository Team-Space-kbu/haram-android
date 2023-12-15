plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.core_ui"
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)
    implementation(Dev.Android.Appcompat.androidAppcompat)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)

    implementation(Dev.Glide.glide)
    implementation(Dev.Glide.glide_okhttp3)
    kapt(Dev.Glide.glide_compiler)
}