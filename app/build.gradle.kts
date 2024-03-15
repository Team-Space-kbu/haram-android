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
    implementation(project(":shared"))
    implementation(project(":domain"))
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigate"))

    implementation(project(":features:main"))
    implementation(project(":features:signin"))
    implementation(project(":features:home"))
    implementation(project(":features:book"))
    implementation(project(":features:mileage"))
    implementation(project(":features:chapel"))
    implementation(project(":features:other"))
    implementation(project(":features:board"))
    implementation(project(":features:timetable"))
    implementation(project(":features:partners"))
    implementation(project(":features:bible"))
    implementation(project(":features:rothem"))
    implementation(project(":features:notice"))


    //KTX
    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Core.splashscreen)
    implementation(Dev.Android.Activity.androidxActivity)
    implementation(Dev.Android.Startup.AndroidStartup)
    implementation(Dev.Kotlin.kotlinxCoroutines)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    implementation(Dev.Timber.timber)
    implementation(Dev.Naver.naverMaps)

    implementation(Dev.Glide.glide)
    implementation(Dev.Glide.glide_okhttp3)
    implementation(Dev.AndroidSvg.androidSvg)
    kapt(Dev.Glide.glide_compiler)
}