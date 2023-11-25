
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(project(":features:home"))

    //KTX
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxNavigationFragment)
    implementation(Libraries.androidxNavigationUi)

    //AndroidX
    implementation(Libraries.androidMaterial)
    implementation(Libraries.androidConstraintlayout)
    implementation(Libraries.androidAppcompat)
    implementation(Libraries.androidLegacy)
    implementation(Libraries.androidLifecycleViewmodel)
    implementation(Libraries.androidLifecycleLivedata)
    implementation(Libraries.androidRecyclerview)

    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)

    //etc
    implementation(Libraries.glide)
    implementation(Libraries.glide_okhttp3)
    kapt(Libraries.glide_compiler)
    implementation(Libraries.kotlinxCoroutines)
    implementation(Libraries.timber)
    implementation(Libraries.naverMaps)
    implementation(Libraries.shimmer)

    //test
    implementation(Libraries.junit)
    implementation(Libraries.androidxTestJunit)
    implementation(Libraries.androidxTestEspresso)
}