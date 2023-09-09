plugins {
    kotlin("android")
    id("com.android.application")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.haram_android"

    defaultConfig {
        applicationId = "com.space.haram_anedroid"
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":shared"))

    //KTX
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxActivity)
    implementation(Libraries.androidxFragment)
    implementation(Libraries.androidxNavigationFragment)
    implementation(Libraries.androidxNavigationUi)

    //AndroidX
    implementation(Libraries.androidMaterial)
    implementation(Libraries.androidAppcompat)
    implementation(Libraries.androidAppcompatResources)
    implementation(Libraries.androidLifecycleViewmodel)
    implementation(Libraries.androidLifecycleLivedata)
    implementation(Libraries.androidLegacy)
    implementation(Libraries.androidConstraintlayout)
    implementation(Libraries.androidAnnotation)
    implementation(Libraries.androidCardview)
    implementation(Libraries.androidRecyclerview)

    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    implementation(Libraries.hiltAndroidGradle)
    kapt(Libraries.hiltCompiler)

    //Retrofit, okhttp3

    //etc
    implementation(Libraries.glide)
    implementation(Libraries.kotlinxCoroutines)
    implementation(Libraries.timber)

    //test
    implementation(Libraries.junit)
    implementation(Libraries.androidxTestJunit)
    implementation(Libraries.androidxTestEspresso)
}