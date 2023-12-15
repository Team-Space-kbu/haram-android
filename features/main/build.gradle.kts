plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:home"))
    implementation(project(":features:board"))
    implementation(project(":features:other"))
    implementation(project(":features:navigate"))

    //KTX
    implementation(Dev.Android.androidxCore)
    implementation(Dev.Android.Legacy.androidLegacy)
    implementation(Dev.Android.Activity.androidxNavigationUi)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)


    //AndroidX
    implementation(Dev.Android.Ui.androidConstraintlayout)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)
}