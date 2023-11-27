
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
    implementation(project(":features:home"))

    //KTX
    implementation(Dev.androidxCore)
    implementation(Dev.androidxNavigationFragment)
    implementation(Dev.androidxNavigationUi)

    //AndroidX
    implementation(Dev.androidMaterial)
    implementation(Dev.androidConstraintlayout)
    implementation(Dev.androidAppcompat)
    implementation(Dev.androidLegacy)
    implementation(Dev.androidLifecycleViewmodel)
    implementation(Dev.androidLifecycleLivedata)
    implementation(Dev.androidRecyclerview)

    //dagger, hilt
    implementation(Dev.hiltAndroid)
    kapt(Dev.hiltCompiler)

    //etc
    implementation(Dev.glide)
    implementation(Dev.glide_okhttp3)
    kapt(Dev.glide_compiler)
    implementation(Dev.kotlinxCoroutines)
    implementation(Dev.timber)
    implementation(Dev.naverMaps)
    implementation(Dev.shimmer)

    //test
    implementation(Dev.junit)
    implementation(Dev.androidxTestJunit)
    implementation(Dev.androidxTestEspresso)
}