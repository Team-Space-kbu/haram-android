
plugins {
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")

}

android {
    namespace = "com.space.biblemon"

    defaultConfig {
        applicationId = "com.space.biblemon"
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
}


dependencies {
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(project(":features:main"))
    implementation(project(":features:home"))
    implementation(project(":features:other"))
    implementation(project(":features:board"))
    implementation(project(":features:navigate"))


    //KTX
    implementation(Dev.Android.androidxCore)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Activity.androidxNavigationUi)
    implementation(Dev.Kotlin.kotlinxCoroutines)

    //AndroidX
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidConstraintlayout)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Android.Appcompat.androidAppcompat)
    implementation(Dev.Android.Legacy.androidLegacy)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    //etc
    implementation(Dev.Glide.glide)
    implementation(Dev.Glide.glide_okhttp3)
    kapt(Dev.Glide.glide_compiler)


    implementation(Dev.Timber.timber)
    implementation(Dev.Naver.naverMaps)

    //test
    implementation(Dev.Test.junit)
    implementation(Dev.Test.androidxTestJunit)
    implementation(Dev.Test.androidxTestEspresso)

}