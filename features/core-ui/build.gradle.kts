plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
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
    implementation(project(":features:navigate"))
    implementation(project(":shared"))

    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)
    implementation(Dev.Android.Appcompat.androidAppcompat)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Android.Ui.androidFlexbox)


    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    implementation(Dev.Glide.glide)
    implementation(Dev.Glide.glide_okhttp3)
    kapt(Dev.Glide.glide_compiler)

    implementation(Dev.Timber.timber)

    implementation("commons-io:commons-io:2.13.0")
    implementation(platform(Dev.FireBase.firebaseBom))
    implementation(Dev.FireBase.firebaseAnalytics)
    implementation(Dev.FireBase.firebaseCrashlytics)
    implementation(Dev.FireBase.firebasePerformance)

}